package jatx.remotekeyboard.client;

import jatx.remotekeyboard.windaemon.WinKeyCodes;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import android.util.Log;

public class DaemonConnector extends Thread {
	public static final int CONNECT_PORT = 10307;
	public static final int SO_TIMEOUT = 3000;
	
	public static final byte PRESS = 1;
	public static final byte RELEASE = 2;
	public static final byte PRESS_RELEASE = 3;
	public static final byte PSEUDO = 16;
	public static final byte DUMMY = (byte)128;
	
	private volatile BlockingQueue<Byte> fifo;
	private volatile boolean mFinishFlag = false;
	
	private volatile WeakReference<MainActivity> ref;
	
	private volatile String mHost;
	
	public DaemonConnector(MainActivity activity) {
		fifo = new ArrayBlockingQueue<Byte>(4096);
		
		ref = new WeakReference<MainActivity>(activity);
		mHost = activity.host;
	}
	
	public void finishThread() {
		mFinishFlag = true;
		interrupt();
	}
	
	public void sendKeyPress(int keyCode) {
		fifo.offer(PRESS);
		fifo.offer((byte)(0x000000FF&keyCode));
	}
	
	public void sendKeyRelease(int keyCode) {
		fifo.offer(RELEASE);
		fifo.offer((byte)(0x000000FF&keyCode));
	}
	
	public void sendKeyPressRelease(int keyCode) {
		fifo.offer(PRESS_RELEASE);
		fifo.offer((byte)(0x000000FF&keyCode));
	}
	
	public void sendPseudoKeyPress(int keyCode) {
		fifo.offer((byte)(PSEUDO|PRESS));
		fifo.offer((byte)(0x000000FF&keyCode));
	}
	
	public void sendPseudoKeyRelease(int keyCode) {
		fifo.offer((byte)(PSEUDO|RELEASE));
		fifo.offer((byte)(0x000000FF&keyCode));
	}
	
	@Override
	public void run() {
		try {
			while (!mFinishFlag) {
				Thread.sleep(100);
				
				Socket s = new Socket();
				session(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
	
	private void session(Socket socket) {
		System.out.println("session start");
		
		OutputStream os = null;
		int byteCounter = 0;
		byte[] word = new byte[2];
		
		int dummyCounter = 0;
		
		try {
			socket.connect(new InetSocketAddress(mHost, CONNECT_PORT), SO_TIMEOUT);
			
			System.out.println("socket connect");
			if (ref.get()!=null) {
				ref.get().connected();
			}
			
			os = socket.getOutputStream();
			
			while (true) {
				Byte b = fifo.poll();
				
				if (b!=null) {
					Log.i("byte", Integer.toString(b.intValue()));
					
					word[byteCounter] = b;
					
					byteCounter = (byteCounter + 1) % 2;
					
					if (byteCounter==0) {
						os.write(word, 0, 2);
						os.flush();
					}
				} else if (byteCounter==0) {
					word[0] = DUMMY;
					word[1] = DUMMY;
					
					dummyCounter = (dummyCounter + 1) % 40;
					
					if (dummyCounter==0 && ref.get()!=null) {
						if (ref.get().isRusLang) {
							word[0] = (byte)(PSEUDO|PRESS);
							word[1] = (byte)(0x000000FF&WinKeyCodes.PSEUDO_KEY_EN_RU); 
						} else {
							word[0] = (byte)(PSEUDO|RELEASE);
							word[1] = (byte)(0x000000FF&WinKeyCodes.PSEUDO_KEY_EN_RU);
						}
					}
					
					os.write(word, 0, 2);
					os.flush();

					Thread.sleep(25);
				}
			}
		} catch (SocketTimeoutException e) {
			System.out.println("Socket Timeout");
			
			if (ref.get()!=null) {
				ref.get().disconnected("Connect Timeout");
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			if (ref.get()!=null) {
				ref.get().disconnected("Disconnect Occured");
			}
		} catch (InterruptedException e) {
			System.out.println("interrupted");
		} catch (Exception e) {
			System.out.println("Other Fucking Exception");
			e.printStackTrace();
		} finally {
			try {
				os.close();
				System.out.println("output stream closed");
			} catch (Exception e) {
				System.out.println("output stream close error");
			}
			
			try {
				socket.close();
				System.out.println("socket closed");
			} catch (Exception e) {
				System.out.println("socket close error");
			}
			
			System.out.println("session end");
		}
	}
}

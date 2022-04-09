package jatx.remotekeyboard.client;

import java.util.ArrayList;
import java.util.List;

import jatx.remotekeyboard.windaemon.WinKeyCodes;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	final static String PREFS_NAME = "RemoteKeyboardPrefsFile";
	final static String DELIM = "#13731#";
	
	public String host;
	public List<String> allHosts;
	int hostIndex;
	
	public DaemonConnector daemonConnector;
	
	public volatile boolean isCtrlPressed = false;
	public volatile boolean isAltPressed = false;
	public volatile boolean isShiftPressed = false;
	public volatile boolean isWinPressed = false;
	public volatile boolean isRusLang = false;
	
	private volatile WifiLock mWifiLock;
	private PowerManager.WakeLock mWakeLock;
	
	private ImageView mWifiOk;
	private ImageView mWifiNo;
	
	//private MainActivity self;
	
	@SuppressWarnings("deprecation")
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		if (Build.VERSION.SDK_INT>=14) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		} 
		
		setContentView(R.layout.activity_main);
		
		//self = this;
		
		//Debug.setCustomExceptionHandler(getApplicationContext());
		
		WifiManager wifiManager =
				(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		mWifiLock = wifiManager.createWifiLock("ssh_client_wifi_lock");
		mWifiLock.setReferenceCounted(false);
		mWifiLock.acquire();
	
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock =
				pm.newWakeLock(
						PowerManager.SCREEN_DIM_WAKE_LOCK,
						"JatxRemoteKeyboard:song-text-power");
		mWakeLock.acquire();
		
		final TextView exitButton = (TextView) findViewById(R.id.exit_button);
	    exitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	      
	    final ImageView icQuestion = (ImageView) findViewById(R.id.ic_question);
	    icQuestion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HelpDialog dialog = HelpDialog.newInstance();
				dialog.show(getSupportFragmentManager(), "help-dialog");
			}
		});
	    
	    mWifiOk = (ImageView) findViewById(R.id.ic_wifi_ok);
	    mWifiNo = (ImageView) findViewById(R.id.ic_wifi_no);
	    
	    KeyButton.init();
		
		initKeyBoard();
		
		SelectHostDialog dialog = SelectHostDialog.newInstance(this);
		dialog.show(getSupportFragmentManager(), "select-host-dialog");		
	}
	
	@Override
	public void onDestroy() {
		if (mWifiLock!=null) {
			mWifiLock.release();
		}
		
		if (mWakeLock!=null) {
			mWakeLock.release();
		}
		
		if (daemonConnector!=null) {
			if (isShiftPressed) {
				daemonConnector.sendKeyRelease(WinKeyCodes.KEY_SHIFT);
			}
			
			if (isCtrlPressed) {
				daemonConnector.sendKeyRelease(WinKeyCodes.KEY_CTRL);
			}
			
			if (isAltPressed) {
				daemonConnector.sendKeyRelease(WinKeyCodes.KEY_ALT);
			}
			
			if (isWinPressed) {
				daemonConnector.sendKeyRelease(WinKeyCodes.KEY_L_WIN);
			}
			
			daemonConnector.sendPseudoKeyPress(WinKeyCodes.PSEUDO_KEY_EXIT);
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			daemonConnector.finishThread();
		}
		
		super.onDestroy();
	}

	public void prepareAndStart() {
		daemonConnector = new DaemonConnector(this);
		daemonConnector.start();
	}
	
	public void connected() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mWifiNo.setVisibility(View.GONE);
				mWifiOk.setVisibility(View.VISIBLE);
				
				Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void disconnected(final String msg) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mWifiOk.setVisibility(View.GONE);
				mWifiNo.setVisibility(View.VISIBLE);
				
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void initKeyBoard() {
		// Fn:
		
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f1, "F1", WinKeyCodes.KEY_F1);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f2, "F2", WinKeyCodes.KEY_F2);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f3, "F3", WinKeyCodes.KEY_F3);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f4, "F4", WinKeyCodes.KEY_F4);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f5, "F5", WinKeyCodes.KEY_F5);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f6, "F6", WinKeyCodes.KEY_F6);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f7, "F7", WinKeyCodes.KEY_F7);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f8, "F8", WinKeyCodes.KEY_F8);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f9, "F9", WinKeyCodes.KEY_F9);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f10, "F10", WinKeyCodes.KEY_F10);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f11, "F11", WinKeyCodes.KEY_F11);
		FnSpecialKeyButton.findInstance(this, R.id.fn_key_f12, "F12", WinKeyCodes.KEY_F12);
		
		// Special:
		
		SpecialKeyButton.findInstance(this, R.id.special_key_back, "BACK", WinKeyCodes.KEY_BACKSPACE);
		SpecialKeyButton.findInstance(this, R.id.special_key_del, "DEL", WinKeyCodes.KEY_DEL);
		SpecialKeyButton.findInstance(this, R.id.special_key_enter, "ENTER", WinKeyCodes.KEY_ENTER);
		SpecialKeyButton.findInstance(this, R.id.special_key_esc, "ESC", WinKeyCodes.KEY_ESCAPE);
		SpecialKeyButton.findInstance(this, R.id.special_key_tab, "TAB", WinKeyCodes.KEY_TAB);
		SpecialKeyButton.findInstance(this, R.id.special_key_home, "HOME", WinKeyCodes.KEY_HOME);
		SpecialKeyButton.findInstance(this, R.id.special_key_end, "END", WinKeyCodes.KEY_END);
		
		SpecialKeyButton.findInstance(this, R.id.special_key_left, "LEFT", WinKeyCodes.KEY_LEFT);
		SpecialKeyButton.findInstance(this, R.id.special_key_down, "DOWN", WinKeyCodes.KEY_DOWN);
		SpecialKeyButton.findInstance(this, R.id.special_key_right, "RIGHT", WinKeyCodes.KEY_RIGHT);
		SpecialKeyButton.findInstance(this, R.id.special_key_up, "UP", WinKeyCodes.KEY_UP);
		
		SwitchableSpecialKeyButton.findInstance(this, R.id.special_key_ctrl, "CTRL", WinKeyCodes.KEY_CTRL);
		SwitchableSpecialKeyButton.findInstance(this, R.id.special_key_alt, "ALT", WinKeyCodes.KEY_ALT);
		SwitchableSpecialKeyButton.findInstance(this, R.id.special_key_win, "WIN", WinKeyCodes.KEY_L_WIN);
		SwitchableSpecialKeyButton.findInstance(this, R.id.special_key_en_ru, "EN/RU", WinKeyCodes.PSEUDO_KEY_EN_RU);
		SwitchableSpecialKeyButton.findInstance(this, R.id.special_key_shift, "SHIFT", WinKeyCodes.KEY_SHIFT);
		
		// Row 0:
		
		KeyButton.findInstance(this, R.id.key_backquote, '`', "`", "~", "ё", "Ё");
		KeyButton.findInstance(this, R.id.key_1, '1',"1", "!", "1", "!");
		KeyButton.findInstance(this, R.id.key_2, '2',"2", "@", "2", "\"");
		KeyButton.findInstance(this, R.id.key_3, '3',"3", "#", "3", "№");
		KeyButton.findInstance(this, R.id.key_4, '4',"4", "$", "4", ";");
		KeyButton.findInstance(this, R.id.key_5, '5',"5", "%", "5", "%");
		KeyButton.findInstance(this, R.id.key_6, '6',"6", "^", "6", ":");
		KeyButton.findInstance(this, R.id.key_7, '7',"7", "&", "7", "?");
		KeyButton.findInstance(this, R.id.key_8, '8',"8", "*", "8", "*");
		KeyButton.findInstance(this, R.id.key_9, '9',"9", "(", "9", "(");
		KeyButton.findInstance(this, R.id.key_0, '0',"0", ")", "0", ")");
		KeyButton.findInstance(this, R.id.key_minus, '-', "-", "_", "-", "_");
		KeyButton.findInstance(this, R.id.key_equals, '=', "=", "+", "=", "+");
		
		// Row 1:
		
		KeyButton.findInstance(this, R.id.key_q, 'q', "q", "Q", "й", "Й");
		KeyButton.findInstance(this, R.id.key_w, 'w', "w", "W", "ц", "Ц");
		KeyButton.findInstance(this, R.id.key_e, 'e', "e", "E", "у", "У");
		KeyButton.findInstance(this, R.id.key_r, 'r', "r", "R", "к", "К");
		KeyButton.findInstance(this, R.id.key_t, 't', "t", "T", "е", "Е");
		KeyButton.findInstance(this, R.id.key_y, 'y', "y", "Y", "н", "Н");
		KeyButton.findInstance(this, R.id.key_u, 'u', "u", "U", "г", "Г");
		KeyButton.findInstance(this, R.id.key_i, 'i', "i", "I", "ш", "Ш");
		KeyButton.findInstance(this, R.id.key_o, 'o', "o", "O", "щ", "Щ");
		KeyButton.findInstance(this, R.id.key_p, 'p', "p", "P", "з", "З");
		KeyButton.findInstance(this, R.id.key_left_bracket, '[', "[", "{", "х", "Х");
		KeyButton.findInstance(this, R.id.key_right_bracket, ']', "]", "}", "ъ", "Ъ");
		KeyButton.findInstance(this, R.id.key_backslash, '\\', "\\", "|", "\\", "/");
		
		// Row 2:
		
		KeyButton.findInstance(this, R.id.key_a, 'a', "a", "A", "ф", "Ф");
		KeyButton.findInstance(this, R.id.key_s, 's', "s", "S", "ы", "Ы");
		KeyButton.findInstance(this, R.id.key_d, 'd', "d", "D", "в", "В");
		KeyButton.findInstance(this, R.id.key_f, 'f', "f", "F", "а", "А");
		KeyButton.findInstance(this, R.id.key_g, 'g', "g", "G", "п", "П");
		KeyButton.findInstance(this, R.id.key_h, 'h', "h", "H", "р", "Р");
		KeyButton.findInstance(this, R.id.key_j, 'j', "j", "J", "о", "О");
		KeyButton.findInstance(this, R.id.key_k, 'k', "k", "K", "л", "Л");
		KeyButton.findInstance(this, R.id.key_l, 'l', "l", "L", "д", "Д");
		KeyButton.findInstance(this, R.id.key_semicolon, ';', ";", ":", "ж", "Ж");
		KeyButton.findInstance(this, R.id.key_quote, '\'', "'", "\"", "э", "Э");
		
		// Row 3:
		
		KeyButton.findInstance(this, R.id.key_z, 'z', "z", "Z", "я", "Я");
		KeyButton.findInstance(this, R.id.key_x, 'x', "x", "X", "ч", "Ч");
		KeyButton.findInstance(this, R.id.key_c, 'c', "c", "C", "с", "С");
		KeyButton.findInstance(this, R.id.key_v, 'v', "v", "V", "м", "М");
		KeyButton.findInstance(this, R.id.key_b, 'b', "b", "B", "и", "И");
		KeyButton.findInstance(this, R.id.key_n, 'n', "n", "N", "т", "Т");
		KeyButton.findInstance(this, R.id.key_m, 'm', "m", "M", "ь", "Ь");
		KeyButton.findInstance(this, R.id.key_comma, ',', ",", "<", "б", "Б");
		KeyButton.findInstance(this, R.id.key_dot, '.', ".", ">", "ю", "Ю");
		KeyButton.findInstance(this, R.id.key_slash, '/', "/", "?", ".", ",");
		
		// Row 4:
		
		KeyButton.findInstance(this, R.id.key_space, ' ', " ", " ", " ", " ");
		
		///////
	}
	
	public void loadSettings() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	
		String allHostsStr = settings.getString("all_IP", "New Daemon");
		String[] allHostsArr = allHostsStr.split(DELIM);
		/*if (allHostsStr.indexOf(DELIM)>0) {
			allHostsArr = allHostsStr.split(DELIM);
		} else {
			allHostsArr = new String[]{"New Daemon"};
		}*/
		
		allHosts = new ArrayList<String>();
		for (int i=0; i<allHostsArr.length; i++) {
			allHosts.add(allHostsArr[i]);
		}
		
		host = settings.getString("IP", "New Daemon");
		
		hostIndex = allHosts.indexOf(host);
		
		Log.i("all hosts", allHostsStr);
		Log.i("host index", Integer.toString(hostIndex));
	}
	
	public void saveSettings() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		
		editor.putString("IP", host);
		
		StringBuilder allHostsBuilder = new StringBuilder();
		allHostsBuilder.append(allHosts.get(0));
		for (int i=1; i<allHosts.size(); i++) {
			allHostsBuilder.append(DELIM);
			allHostsBuilder.append(allHosts.get(i));
		}
		editor.putString("all_IP", allHostsBuilder.toString());
		
		editor.commit();
	}
}

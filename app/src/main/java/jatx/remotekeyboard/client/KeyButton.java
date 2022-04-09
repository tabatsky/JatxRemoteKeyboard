package jatx.remotekeyboard.client;

import jatx.remotekeyboard.windaemon.CharMappings;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class KeyButton extends AppCompatButton {
	public static boolean isRussianLang;
	public static boolean isBigLetters;
	
	private static List<KeyButton> allButtons;
	
	private MainActivity mActivity;
	
	private int mKeyValue;
	private String mStringValue;
	
	private String mEngSmall;
	private String mEngBig;
	private String mRusSmall;
	private String mRusBig;
	
	public static void init() {
		allButtons = new ArrayList<KeyButton>();
		isRussianLang = false;
		isBigLetters = false;
	}
	
	public KeyButton(Context context) {
		super(context);
	}
	
	public KeyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public KeyButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@SuppressWarnings("deprecation")
	public static KeyButton findInstance(MainActivity activity, int id, char charValue, String engSmall, String engBig, String rusSmall, String rusBig) {
		final KeyButton button = (KeyButton) activity.findViewById(id);
		
		if (Build.VERSION.SDK_INT >= 14) {
			button.setAllCaps(false);
		}
			
		int[] mapping = CharMappings.getCharMapping(charValue);
		
		button.mKeyValue = mapping[mapping.length-1];
		button.mStringValue = engSmall;
		button.setText(button.mStringValue);
		
		button.mActivity = activity;
		
		button.mEngSmall = engSmall;
		button.mEngBig = engBig;
		button.mRusSmall = rusSmall;
		button.mRusBig = rusBig;
		
		button.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.common_key_button));
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("button pressed", button.mStringValue);
				
				button.mActivity.daemonConnector.sendKeyPressRelease(button.mKeyValue);
			}
		});
		
		allButtons.add(button);
		
		return button;
	}
	
	private void updateLabel() {
		if (isRussianLang) {
			if (isBigLetters) {
				mStringValue = mRusBig;
			} else {
				mStringValue = mRusSmall;
			}
		} else {
			if (isBigLetters) {
				mStringValue = mEngBig;
			} else {
				mStringValue = mEngSmall;
			}
		}
		
		setText(mStringValue);
	}
	
	public static void switchLang() {
		isRussianLang = !isRussianLang;
		
		for (KeyButton button: allButtons) {
			button.updateLabel();
		}
	}
	
	public static void switchBigLetters() {
		isBigLetters = !isBigLetters;
		
		for (KeyButton button: allButtons) {
			button.updateLabel();
		}
	}

}

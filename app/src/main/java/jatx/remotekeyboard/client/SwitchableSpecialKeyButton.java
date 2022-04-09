package jatx.remotekeyboard.client;

import jatx.remotekeyboard.windaemon.WinKeyCodes;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class SwitchableSpecialKeyButton extends AppCompatButton {
	private MainActivity mActivity;
	
	private int mKeyValue;
	
	private boolean isPressed = false;
	
	public SwitchableSpecialKeyButton(Context context) {
		super(context);
	}
	
	public SwitchableSpecialKeyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwitchableSpecialKeyButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@SuppressWarnings("deprecation")
	public static SwitchableSpecialKeyButton findInstance(MainActivity activity, int id, String label, int keyValue) {
		final SwitchableSpecialKeyButton button = (SwitchableSpecialKeyButton) activity.findViewById(id);
		
		button.mActivity = activity;
		button.mKeyValue = keyValue;
		
		button.setText(label);
		
		button.setTextSize(TypedValue.COMPLEX_UNIT_PX, 
				activity.getResources()
				.getDimensionPixelSize(R.dimen.special_button_text_size));
		
		button.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.switchable_button_not_pressed));
		button.setTextColor(activity.getResources().getColor(R.color.black));
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				button.switchPressed();
				
				switch (button.mKeyValue) {
				
				case WinKeyCodes.KEY_SHIFT:
					KeyButton.switchBigLetters();
					button.mActivity.isShiftPressed = !button.mActivity.isShiftPressed; 
					break;
					
				case WinKeyCodes.KEY_CTRL:
					button.mActivity.isCtrlPressed = !button.mActivity.isCtrlPressed;
					break;
				
				case WinKeyCodes.KEY_ALT:
					button.mActivity.isAltPressed = !button.mActivity.isAltPressed;
					break;
				
				case WinKeyCodes.KEY_L_WIN:
					button.mActivity.isWinPressed = !button.mActivity.isWinPressed;
					break;
					
				case WinKeyCodes.PSEUDO_KEY_EN_RU:
					button.mActivity.isRusLang = !button.mActivity.isRusLang;
					KeyButton.switchLang();
					break;
					
				default:
					break;
				}
				
				if (button.isPressed) {
					if ((button.mKeyValue&WinKeyCodes.PSEUDO_KEY_MASK)!=0) {
						button.mActivity.daemonConnector.sendPseudoKeyPress(button.mKeyValue);
					} else {
						button.mActivity.daemonConnector.sendKeyPress(button.mKeyValue);
					}
				} else {
					if ((button.mKeyValue&WinKeyCodes.PSEUDO_KEY_MASK)!=0) {
						button.mActivity.daemonConnector.sendPseudoKeyRelease(button.mKeyValue);
					} else {
						button.mActivity.daemonConnector.sendKeyRelease(button.mKeyValue);
					}
				}
			}
		});
		
		return button;
	}
	
	@SuppressWarnings("deprecation")
	public void switchPressed() {
		isPressed = !isPressed;
		
		if (isPressed) {
			setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.switchable_button_pressed));
			setTextColor(mActivity.getResources().getColor(R.color.white));
		} else {
			setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.switchable_button_not_pressed));
			setTextColor(mActivity.getResources().getColor(R.color.black));
		}
	}
}

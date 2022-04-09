package jatx.remotekeyboard.client;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class FnSpecialKeyButton extends AppCompatButton {
	private MainActivity mActivity;
	
	private int mKeyValue;
	
	public FnSpecialKeyButton(Context context) {
		super(context);
	}
	
	public FnSpecialKeyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FnSpecialKeyButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	
	@SuppressWarnings("deprecation")
	public static FnSpecialKeyButton findInstance(MainActivity activity, int id, final String label, int keyValue) {
		final FnSpecialKeyButton button = (FnSpecialKeyButton) activity.findViewById(id);
		
		button.mActivity = activity;
		button.mKeyValue = keyValue;
		
		button.setText(label);
		
		button.setTextSize(TypedValue.COMPLEX_UNIT_PX, 
				activity.getResources()
				.getDimensionPixelSize(R.dimen.special_button_text_size));
		
		button.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.fn_special_key_button));
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("special button pressed", label);
				
				button.mActivity.daemonConnector.sendKeyPressRelease(button.mKeyValue);
			}
		});
		
		return button;
	}
}
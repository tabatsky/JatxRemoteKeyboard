package jatx.remotekeyboard.client;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class HelpDialog extends DialogFragment {
	//private MainActivity mActivity;
	
	public static HelpDialog newInstance() {
		HelpDialog dialog = new HelpDialog();
		
		//dialog.mActivity = activity;
		
		//dialog.setCancelable(false);
		
	    return dialog;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL;
        int theme = androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog;
        
        setStyle(style, theme);
    }
	
	@SuppressWarnings("deprecation")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getDialog().setTitle("");

        View v = inflater.inflate(R.layout.dialog_help, container, false);
        
        final Button closeButton = (Button) v.findViewById(R.id.help_close);
        closeButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_button));
        closeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
        
        final TextView reviewApp = (TextView) v.findViewById(R.id.review_app);
        reviewApp.setText(Html.fromHtml("<u>" + getString(R.string.review_app) + "</u>"));
        reviewApp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
			    	startActivity(new Intent(Intent.ACTION_VIEW, 
			    		Uri.parse("market://details?id=jatx.remotekeyboard.client")));
				} catch (android.content.ActivityNotFoundException anfe) {
			    	startActivity(new Intent(Intent.ACTION_VIEW, 
			    		Uri.parse("https://play.google.com/store/apps/details?id=jatx.remotekeyboard.client")));
				}
			}
		});
        
        final TextView daemonWin32 = (TextView) v.findViewById(R.id.daemon_win32);
        daemonWin32.setText(Html.fromHtml("<u>" + getString(R.string.daemon_win32) + "</u>"));
        daemonWin32.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String url = "https://yadi.sk/d/Y2n4U6dVhSKoB";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});
        
        final TextView daemonWin64 = (TextView) v.findViewById(R.id.daemon_win64);
        daemonWin64.setText(Html.fromHtml("<u>" + getString(R.string.daemon_win64) + "</u>"));
        daemonWin64.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String url = "https://yadi.sk/d/jooiqVOghSKpV";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});
        
        final TextView devSite = (TextView) v.findViewById(R.id.dev_site);
        devSite.setText(Html.fromHtml("<u>" + getString(R.string.dev_site) + "</u>"));
        devSite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String url = "http://tabatsky.ru";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});
        
        return v;
	}
}

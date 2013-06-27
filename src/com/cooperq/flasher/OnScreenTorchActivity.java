package com.cooperq.flasher;


import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import com.cooperq.yat.R;

public class OnScreenTorchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_on_screen_torch);		
	}
	
	protected void onResume(){
		super.onResume();
		Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

		WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
		layoutParams.screenBrightness = 1.0F; // set 50% brightness
		getWindow().setAttributes(layoutParams);
	}

	protected void onPause(){
		super.onPause();
		Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);

		
	}
}

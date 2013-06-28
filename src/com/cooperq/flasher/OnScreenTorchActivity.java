package com.cooperq.flasher;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import com.cooperq.yat.R;

public class OnScreenTorchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	protected void onResume(){
		super.onResume();
		Intent intent = getIntent();
		boolean night_vision = intent.getBooleanExtra(MainActivity.NIGHT_VISION, false);
		if(night_vision){
			setContentView(R.layout.night_vision);		
		} else {
			setContentView(R.layout.activity_on_screen_torch);		
		}
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

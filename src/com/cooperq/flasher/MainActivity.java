package com.cooperq.flasher;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.cooperq.yat.R;

public class MainActivity extends Activity {
	public final static String NIGHT_VISION = "com.cooperq.flasher.NIGHT_VISION";
	private Camera mCam;
	private boolean mIsLightOn;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View powerButton = findViewById(R.id.power_button);
        powerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.this.startNightVisionTorch(v);
			}
		});
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void startCameraFlash(View view){
    	if(!mIsLightOn){
 			mCam = Camera.open();
 	 		Parameters p = mCam.getParameters();
 	 		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
 	 		mCam.setParameters(p);
 	 		mCam.startPreview();
 	 		mIsLightOn = true;
 		} else {
 			if(mCam != null){
 				mCam.stopPreview();
 				mCam.release();
 				mCam = null;
 				mIsLightOn = false;
 			}
 		}  
        

    }
    
    public void startOnScreenTorch(View view){
    	Intent intent = new Intent(this, OnScreenTorchActivity.class);
    	intent.putExtra(NIGHT_VISION, false);
    	startActivity(intent);
    }
    
    public void startNightVisionTorch(View view){
    	Intent intent = new Intent(this, OnScreenTorchActivity.class);
    	intent.putExtra(NIGHT_VISION, true);
    	startActivity(intent);
    }
}

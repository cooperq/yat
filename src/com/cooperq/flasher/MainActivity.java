package com.cooperq.flasher;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.Menu;
import android.view.View;
import android.widget.RemoteViews;

import com.cooperq.yat.R;

public class MainActivity extends Activity {
	public final static String NIGHT_VISION = "com.cooperq.flasher.NIGHT_VISION";
	private Camera cam;
	private boolean light_on;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void startCameraFlash(View view){
    	if(!light_on){
 			cam = Camera.open();
 	 		Parameters p = cam.getParameters();
 	 		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
 	 		cam.setParameters(p);
 	 		cam.startPreview();
 	 		light_on = true;
 		} else {
 			if(cam != null){
 				cam.stopPreview();
 				cam.release();
 				cam = null;
 				light_on = false;
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

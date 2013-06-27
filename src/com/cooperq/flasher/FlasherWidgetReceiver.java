package com.cooperq.flasher;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.RemoteViews;
import com.cooperq.yat.R;


public class FlasherWidgetReceiver extends BroadcastReceiver {
	private static Camera cam;
	private static boolean light_on;

	@Override
	public void onReceive(Context context, Intent intent) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.flasher_widget);
		if(!light_on){
			views.setImageViewResource(R.id.imageButton1, R.drawable.flash_on);
		} else {
			views.setImageViewResource(R.id.imageButton1, R.drawable.flash_off);
		}
		
		AppWidgetManager wManager = AppWidgetManager.getInstance(context);
		wManager.updateAppWidget(new ComponentName(context, FlasherWidgetProvider.class), views);
		
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

}

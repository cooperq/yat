package com.cooperq.flasher;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.widget.RemoteViews;
import com.cooperq.yat.R;

public class FlasherWidgetProvider extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Create an Intent to launch ExampleActivity
        
        Intent rcvr = new Intent(context, FlasherWidgetReceiver.class);
        Intent activity = new Intent(context, OnScreenTorchActivity.class);
        rcvr.setAction("COM_FLASHLIGHT");
        rcvr.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        
        

        // Get the layout for the App Widget and attach an on-click listener
        // to the button
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.flasher_widget);
        try{
        	Camera cam = Camera.open();
        	cam.release();
            PendingIntent flashTorchIntent = PendingIntent.getBroadcast(context, 0, rcvr, 0);
        	views.setOnClickPendingIntent(R.id.imageButton1, flashTorchIntent);
        }catch(Exception E){
            PendingIntent onScreenTorch = PendingIntent.getActivity(context, 0, activity, 0);
        	views.setOnClickPendingIntent(R.id.imageButton1, onScreenTorch);
        }

        // Tell the AppWidgetManager to perform an update on the current app widget
        appWidgetManager.updateAppWidget(appWidgetIds, views);
	}
}

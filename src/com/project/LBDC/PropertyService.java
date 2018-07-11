package com.project.LBDC;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class PropertyService extends Service {
	
	private static final String TAG = "MyService";
	private Handler handler;
	static NotificationManager nm;
	
	GetMessage gm; 
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		gm = new GetMessage();
		nm = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
		Toast.makeText(this, "Property Service Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Property Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		
	}
	
	
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "Property Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("enableNotifications", true)) {
			if(new Utils().isLogin(this))
				getMessages();
			else
				this.stopSelf();
		}
	}
	
	public void getMessages() {
		
        (new GetMessage()).execute((Object)null);
        			
	}
	@SuppressWarnings("unchecked")
    private class GetMessage extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {
            	Thread t = new Thread() {
                	@Override
        			public void run() {
                		try {
                			synchronized(this) {
                				wait(60 * 1000);
                				Log.v("Property", "Thread wait complete");
                			}
                		}
                		catch(Exception e) {
                			e.printStackTrace();
                		}
                		finally {
                			(new GetMessage()).execute((Object)null);
                			stop();
                		}
                	}
                };
        		t.start();
            	return ServerInterface.getUnreadMessageCount(new Utils().getUsername(PropertyService.this));
            	
            }

            /**
             * Parse the String result, and create a new array adapter for the list
             * view.
             */
            protected void onPostExecute(Object objResult) {
            	 // check to make sure we're dealing with a string
                if(objResult != null && objResult instanceof String) { 
                		String result = (String) objResult;
                        if(!result.contains("None")) {
                        	Log.v("Property", "Calling notification!");
                        	int count = Integer.parseInt(result);
                        	Notification notification = new Notification(R.drawable.icon, "Property", System.currentTimeMillis());
                        	//Intent notificationIntent = new Intent(PropertyService.this, MessagesActivity.class);
                        	//PendingIntent contentIntent = PendingIntent.getActivity(PropertyService.this, 0, notificationIntent, 0);
                        	//notification.setLatestEventInfo(PropertyService.this, "Echo", count + " unread messages!", contentIntent);
                        	if(count!=0)
                        	nm.notify(1, notification);
                        }
                		
                        else {
                        	Log.v("Property", "No messages!");
                        	Toast.makeText(PropertyService.this, "No messages!", Toast.LENGTH_SHORT).show();
                        	
                        }
                }
            }

    }


}

package com.project.LBDC;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity implements OnClickListener {
	
	public static String locMsg;
	Button Discounts, Logout,Delete;
	TextView userName;
	ProgressDialog dialog;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Discounts = (Button) findViewById(R.id.btnDiscounts);
        Logout = (Button) findViewById(R.id.btnlogout);
        
        Delete = (Button) findViewById(R.id.btnDelete);
        userName = (TextView) findViewById(R.id.txtUserName);
        
        Discounts.setOnClickListener(this);
        Logout.setOnClickListener(this);
        Delete.setOnClickListener(this);   
      
        LBDCActivity.mainemail = new Utils().getUsername(this);
        userName.setText("Welcome: " + new Utils().getUsername(this));
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Logging out...");
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnDiscounts: 
		startActivity(new Intent(this, MidForm.class));
		break;
		
		case R.id.btnlogout: 
			logout();
		break;
		case R.id.btnDelete:
			deleteAccount();
		break;
		
		}
				
	}
	
	public void logout() {
		dialog.show();
		(new LogoutTask()).execute((Object)null);
	}

	public void deleteAccount() {
		dialog.setMessage("Deleting account...");
		dialog.show();
		(new DeleteAccountTask()).execute((Object)null);
	}
	
	@SuppressWarnings("unchecked")
    private class LogoutTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
                    return ServerInterface.logout(new Utils().getUsername(ProfileActivity.this), LBDCActivity.mainpassword);
            }

            /**
             * Parse the String result, and create a new array adapter for the list
             * view.
             */
            protected void onPostExecute(Object objResult) {
                    // check to make sure we're dealing with a string
                    if(objResult != null && objResult instanceof String) {                          
                            String result = (String) objResult;
                            if(result.contains("Logged out")) {
                            	Toast.makeText(ProfileActivity.this, "Logout successful!", Toast.LENGTH_SHORT).show();
                            	new Utils().setLogin(ProfileActivity.this, false);
                            	if(new Utils().isPropertyServiceRunning(ProfileActivity.this)) {
                            		stopService(new Intent(ProfileActivity.this, PropertyService.class));
                            	}
                            	startActivity(new Intent(ProfileActivity.this, LBDCActivity.class));
                            	finish();
                            }
                            else {
                            	
                            }
                    }
                    dialog.dismiss();
            }

    }

	@SuppressWarnings("unchecked")
    private class DeleteAccountTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
                    return ServerInterface.deleteAccount(new Utils().getUsername(ProfileActivity.this));
            }

            /**
             * Parse the String result, and create a new array adapter for the list
             * view.
             */
            protected void onPostExecute(Object objResult) {
                    // check to make sure we're dealing with a string
                    if(objResult != null && objResult instanceof String) {                          
                            String result = (String) objResult;
                            if(result.contains("Done")) {
                            	Toast.makeText(ProfileActivity.this, "Account deleted!!", Toast.LENGTH_SHORT).show();
                            	new Utils().setLogin(ProfileActivity.this, false);
                            	if(new Utils().isPropertyServiceRunning(ProfileActivity.this)) {
                            		stopService(new Intent(ProfileActivity.this, PropertyService.class));
                            	}
                            	startActivity(new Intent(ProfileActivity.this, LBDCActivity.class));
                            	finish();
                            }
                            else {
                            	
                            }
                    }
                    dialog.dismiss();
            }

    }

}

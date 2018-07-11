package com.project.LBDC;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LBDCActivity extends Activity implements OnClickListener  {
    /** Called when the activity is first created. */
	EditText email, password;
	Button Login;
	ProgressDialog dialog;
	public static String mainemail;
	public static String mainpassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginx);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        email=(EditText)findViewById(R.id.editText1);
        password=(EditText)findViewById(R.id.editText2);
        Login=(Button)findViewById(R.id.btnLogin);
       
        Login.setOnClickListener(this);
        email.setText(new Utils().getUsername(this));
        password.requestFocus();
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Logging in...");
        
        registerScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), register.class);
				startActivity(i);
			}
		});
       
    }

	public void onClick(View arg0) {
		mainemail = email.getText().toString();
		mainpassword = password.getText().toString();		
		dialog.show();
		(new GetLoginTask()).execute((Object)null);
		
	}
	
	

	 @SuppressWarnings("unchecked")
	    private class GetLoginTask extends AsyncTask {

	            /**
	             * Let's make the http request and return the result as a String.
	             */
	            protected String doInBackground(Object... args) {                       
	                    return ServerInterface.getLogin(email.getText().toString(), password.getText().toString());
	            }

	            /**
	             * Parse the String result, and create a new array adapter for the list
	             * view.
	             */
	            protected void onPostExecute(Object objResult) {
	                    // check to make sure we're dealing with a string
	                    if(objResult != null && objResult instanceof String) {                          
	                            String result = (String) objResult;
	                            if(result.contains("true")) {
	                            	new Utils().setLogin(LBDCActivity.this, true);
	                            	new Utils().setUsername(LBDCActivity.this, mainemail);
	                            	startActivity(new Intent(LBDCActivity.this, ProfileActivity.class));
	                            	if(new Utils().isPropertyServiceRunning(LBDCActivity.this)) {
	                            		stopService(new Intent(LBDCActivity.this, PropertyService.class));
	                            	}
	                            	startService(new Intent(LBDCActivity.this, PropertyService.class));
	                            	finish();
	                            }
	                            else {
	                            	Toast.makeText(LBDCActivity.this, "Incorrect Login!", Toast.LENGTH_SHORT).show();
	                            }
	                    }
	                    else{
	                    	Toast.makeText(LBDCActivity.this, "NULL", Toast.LENGTH_SHORT).show();
	                    }
	                    dialog.dismiss();
	            }

	    }

}

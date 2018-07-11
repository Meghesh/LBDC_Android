package com.project.LBDC;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends Activity{

	EditText name, email, age, password,phone;
	Button register, clear;
	TextView loginScreen;
	ProgressDialog dialog;
	CheckBox check;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		 name=(EditText)findViewById(R.id.reg_fullname);
	        email=(EditText)findViewById(R.id.reg_email);
	        password=(EditText)findViewById(R.id.reg_password);
	        age=(EditText)findViewById(R.id.reg_age);
	        phone=(EditText)findViewById(R.id.reg_Phone);
		register = (Button) findViewById(R.id.btnRegister);
		register.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				//Toast.makeText(register.this, "called", Toast.LENGTH_SHORT).show();
				/*if(!(name.getText().toString() =="") && !(email.getText().toString()=="") && !(password.getText().toString()=="") && !(age.getText().toString()=="") && !(phone.getText().toString()==""))
				{	*/
						
						if(email.getText().toString().contains("@") && email.getText().toString().contains("."))
						{	
							if(phone.getText().toString().length()==10 )//&& phone.getText().toString().contains("[0-9]")==true )
							{
								
								
								if(password.getText().toString().length()>=10)
								{
								if(check.isChecked() == true)
								{
									dialog.show();
									(new RegisterTask()).execute((Object)null);
								}
								else
									alert();
									
							}
								else
								{
									alert7();
									}
								}
							else
							{
								alert4();
							
							}
						}
						else
						{	alert3();
							
						}
				}
			/*	
				else
				{
					alert1();
				
				}  
				
				}*/
		});
		check = (CheckBox) findViewById(R.id.checked);
		dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Registering User...");
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        
        loginScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), LBDCActivity.class);
				startActivity(i);
			}
		});
        
	}
	
	
	  public void alert()
      {
      	AlertDialog alertDialog = new AlertDialog.Builder(register.this).create();

  // Setting Dialog Title
  alertDialog.setTitle("Warning");

  // Setting Dialog Message
  alertDialog.setMessage("In Order to Register Your Property You Must Accept The Terms & Conditions");

  // Setting Icon to Dialog
  alertDialog.setIcon(R.drawable.warn);

  // Setting OK Button
  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
          // Write your code here to execute after dialog closed
          Toast.makeText(getApplicationContext(), "Select Terms & Conditions", Toast.LENGTH_SHORT).show();
          }
  });
//Showing Alert Message
  alertDialog.show();
 
      	
      }
  
  public void alert1()
  {
	  AlertDialog alertDialog = new AlertDialog.Builder(register.this).create();

	  alertDialog.setTitle("Warning");
	  // Setting Dialog Message
	  alertDialog.setMessage("All fields are compulsory");

	  // Setting Icon to Dialog
	  alertDialog.setIcon(R.drawable.warn);

	  // Setting OK Button
	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
      // Write your code here to execute after dialog closed

      }
});

// Showing Alert Message
	  alertDialog.show();
	  
  	
  }
  
  
  public void alert7()
  {
	  AlertDialog alertDialog = new AlertDialog.Builder(register.this).create();

	  alertDialog.setTitle("Warning");
	  // Setting Dialog Message
	  alertDialog.setMessage("Password Weak");

	  // Setting Icon to Dialog
	  alertDialog.setIcon(R.drawable.warn);

	  // Setting OK Button
	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
      // Write your code here to execute after dialog closed

      }
});

// Showing Alert Message
	  alertDialog.show();
	  
  	
  }
  
 /* public void alert2()
  {
	  txtPass1.setText("");
	  txtPass2.setText("");
	  
	  AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();

	  alertDialog.setTitle("Warning");
	  // Setting Dialog Message
	  alertDialog.setMessage("Entered passwords do not match");

	  // Setting Icon to Dialog
	  alertDialog.setIcon(R.drawable.warn);

	  // Setting OK Button
	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
      // Write your code here to execute after dialog closed
      Toast.makeText(getApplicationContext(), "Re-enter passwords", Toast.LENGTH_SHORT).show();
      }
});

	  
// Showing Alert Message
	  alertDialog.show();
	  
  	
  }*/

  public void alert3()
  {
	  email.setText("");
	  AlertDialog alertDialog = new AlertDialog.Builder(register.this).create();

	  alertDialog.setTitle("Warning");
	  // Setting Dialog Message
	  alertDialog.setMessage("Entered Email id incorrect ");

	  // Setting Icon to Dialog
	  alertDialog.setIcon(R.drawable.warn);

	  // Setting OK Button
	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
      // Write your code here to execute after dialog closed
      Toast.makeText(getApplicationContext(), "Re-enter Email", Toast.LENGTH_SHORT).show();
      }
});
	// Showing Alert Message
	  alertDialog.show();
	 
  	
  }
  public void alert4()
  {
	  phone.setText("");
	  AlertDialog alertDialog = new AlertDialog.Builder(register.this).create();

	  alertDialog.setTitle("Warning");
	  // Setting Dialog Message
	  alertDialog.setMessage("You must enter 10 digit number and enter only numbers");

	  // Setting Icon to Dialog
	  alertDialog.setIcon(R.drawable.warn);

	  // Setting OK Button
	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
      // Write your code here to execute after dialog closed
      Toast.makeText(getApplicationContext(), "Re-enter phone number", Toast.LENGTH_SHORT).show();
      }
});
	// Showing Alert Message
	  alertDialog.show();
	 
  	
  }

	
	@SuppressWarnings("unchecked")
    private class RegisterTask extends AsyncTask {
			
            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
                    return ServerInterface.getRegister(name.getText().toString(), age.getText().toString(), phone.getText().toString(), email.getText().toString(), password.getText().toString());
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
                            	startActivity(new Intent(register.this, LBDCActivity.class));
                            }
                            else {
                            	
                            }
                    }
                    Toast.makeText(register.this, "Register successful!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
            
            }
			
          
            
            
    }

}

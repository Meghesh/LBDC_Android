package com.project.LBDC;

import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShopDetails extends Activity {

	static TextView name1, code, info, address1, contact;

	ProgressDialog dialog;
	static String address,name;// add;
	String add;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		name1 = (TextView)findViewById(R.id.ShopName);		
		code = (TextView)findViewById(R.id.DiscountCode);
		info = (TextView)findViewById(R.id.DiscountInfo);
		address1 = (TextView)findViewById(R.id.ShopAddress);
		contact = (TextView)findViewById(R.id.ShopNumber);
		
		
		dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Details...");
		address=getIntent().getStringExtra("address");
		//address = "sdsd";
		
		add = (String)address;
		add = add.trim();
		Toast.makeText(ShopDetails.this, add, Toast.LENGTH_SHORT).show();
		
		(new GetInfoTask()).execute((Object)null);
		dialog.show();
	}
	

	@SuppressWarnings("unchecked")
    private class GetInfoTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {   
            	//Toast.makeText(ShopDetails.this, address, Toast.LENGTH_SHORT).show();
        		
                    return ServerInterface.getInformation(add);
            }

            /**
             * Parse the String result, and create a new array adapter for the list
             * view.
             */
            protected void onPostExecute(Object objResult) {
                    // check to make sure we're dealing with a string
            	Toast.makeText(ShopDetails.this, "inside toast", Toast.LENGTH_SHORT).show();
                    if(objResult != null && objResult instanceof String) { 	
                     
                    	
                    
                    		//String a = (String) objResult;
                    		String data[] = ((String)objResult).split(":");
                           // Toast.makeText(ShopDetails.this, a, Toast.LENGTH_SHORT).show();
                            String data1[] = new String[5];
                            for(int i = 0;i<5;i++)
                            {
                            	data1[i] = data[i];
                            }
                            
                    		
                    		name1.setText("Name: " + data1[0]);
                            code.setText("Code: " + data1[1]);
                            info.setText("Info: " + data1[2]);
                            address1.setText("Address: " + data1[3]);
                            contact.setText("Contact: " + data1[4]);
                            
                            dialog.dismiss();
                    	
                    	
                    }}
                    
	}
	}
           

    



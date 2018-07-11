package com.project.LBDC;


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

public class ShopActivity extends Activity{

	static TextView ShopName, CouponCode, CouponInfo, Address,Phone;
	ProgressDialog dialog;
	static String address;
	Double lat,lon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		ShopName = (TextView)findViewById(R.id.ShopName);	
		CouponCode = (TextView)findViewById(R.id.DiscountCode);
		CouponInfo = (TextView)findViewById(R.id.DiscountInfo);
		Address = (TextView)findViewById(R.id.ShopAddress);
		Phone = (TextView)findViewById(R.id.ShopNumber);
		
		
		
		dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Details...");
		address=getIntent().getStringExtra("address");
		
		Toast.makeText(ShopActivity.this, address, Toast.LENGTH_SHORT).show();
		(new GetInfoTask()).execute((Object)null);
		dialog.show();
	}
	



	@SuppressWarnings("unchecked")
    private class GetInfoTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
                    return ServerInterface.getInfo(ShopActivity.address);
            }

            /**
             * Parse the String result, and create a new array adapter for the list
             * view.
             */
            protected void onPostExecute(Object objResult) {
                    // check to make sure we're dealing with a string
            	Toast.makeText(ShopActivity.this, "inside toast", Toast.LENGTH_SHORT).show();
                    if(objResult != null && objResult instanceof String) { 	
                    	  String data[] = ((String)objResult).split(":");
                          ShopName.setText("Name: " + data[0]);
                          CouponCode.setText("Code: " + data[1]);
                          CouponInfo.setText("Info: " + data[2]);
                          Address.setText("Address: " + data[3]);
                          Phone.setText("Contact: " + data[4]);
                            
                    }
            }
	}
           
}

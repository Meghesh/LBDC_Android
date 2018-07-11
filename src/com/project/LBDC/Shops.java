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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Shops extends Activity{
	

	public TextView ShopName, ShopAddress, ShopNumber, DiscountCode, DiscountInfo;
	Button btnMap, btnMsg;
	ProgressDialog dialog;
	static String ShopName1;
	static String address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		ShopName = (TextView)findViewById(R.id.ShopName);		
		ShopAddress = (TextView)findViewById(R.id.ShopAddress);
		ShopNumber = (TextView)findViewById(R.id.ShopNumber);
		DiscountCode = (TextView)findViewById(R.id.DiscountCode);
		DiscountInfo = (TextView)findViewById(R.id.DiscountInfo);
		
		address=getIntent().getStringExtra("address");
		Toast.makeText(Shops.this, address, Toast.LENGTH_SHORT).show();
		
		
		
		dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Shop Details...");
		
		(new GetInfo()).execute((Object)null);

		dialog.show();
	
	
	}
	

	

	@SuppressWarnings("unchecked")
    private class GetInfo extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
                    return ServerInterface.getInfo(Shops.address);
            }

            /**
             * Parse the String result, and create a new array adapter for the list
             * view.
             */
            protected void onPostExecute(Object objResult) {
                    // check to make sure we're dealing with a string
                    if(objResult != null && objResult instanceof String) { 	
                            String data1[] = ((String)objResult).split(".");
                            ShopName.setText("Name: " + data1[0]);
                            DiscountCode.setText("Code: " + data1[1]);
                            DiscountInfo.setText("Info: " + data1[2]);
                            ShopAddress.setText("Address: " + data1[3]);
                            ShopNumber.setText("Contact: " + data1[4]);
                            
                    }
            }
	}
           

}
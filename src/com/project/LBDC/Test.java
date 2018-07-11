package com.project.LBDC;





import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Test extends Activity{

	ProgressDialog dialog;
	Button btngo;
	TextView name,code,info,addr,contact;
	static String address;
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	    	
	    
	    	
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.discount);
	        //Intent intent = getIntent();
	        
	        address = getIntent().getStringExtra("address");
	        name = (TextView)findViewById(R.id.ShopName);		
			code = (TextView)findViewById(R.id.DiscountCode);
			info = (TextView)findViewById(R.id.DiscountInfo);
			addr = (TextView)findViewById(R.id.ShopAddress);
			contact = (TextView)findViewById(R.id.ShopNumber);
			
			
			dialog = new ProgressDialog(this);
	        dialog.setCancelable(false);
	        dialog.setMessage("Loading Details...");
			
	        (new GetDetails()).execute((Object)null); 
	
}
	    
	    
	    
	    
	    
	    @SuppressWarnings("unchecked")
	    private class GetDetails extends AsyncTask {

			@Override
			protected Object doInBackground(Object... arg0) {
				// TODO Auto-generated method stub
				return ServerInterface.getInformation(address);
			}
	    	
			 protected void onPostExecute(Object objResult) {
                 // check to make sure we're dealing with a string
                 if(objResult != null && objResult instanceof String) { 	
                         String data[] = ((String)objResult).split(":");
                         name.setText("Name: " + data[0]);
                 }
	    	
	    	
	    }
	    
}}
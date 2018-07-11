package com.project.LBDC;



import java.io.IOException;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.*;
import android.view.View.OnClickListener;

public class MidForm extends Activity implements OnClickListener{
	
Button btngo;

ProgressDialog dialog,dlg;	 
public Button  btnTcl;
public TextView lat;
public double lt;
public double lng;
public String Text;
public String l;
public String ln,location;
Geocoder geo;

public String name;
public String ss2,ss4;
public String ss1,ss3;
public Spinner sp3, sp1;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
       
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.midform);
        geo= new Geocoder(this);
        
        
       /* name = new Utils().getUsername(MidForm.this);
        Toast.makeText(MidForm.this, name, Toast.LENGTH_SHORT).show();
        dialog.dismiss();*/
        
        dialog = new ProgressDialog(this);
		dialog.setCancelable(false);
        dialog.setMessage("Finding Shops");
        
       
        btngo = (Button) findViewById(R.id.btngo);
        btngo.setOnClickListener(this);
        
        
        btnTcl = (Button) findViewById(R.id.btnGps);
        lat = (TextView) findViewById(R.id.text_view);
        
        btnTcl.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				 //gps
		        /* Use the LocationManager class to obtain GPS locations */
		        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

		        LocationListener mlocListener = new MyLocationListener();
		        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
		        //gps
		        dlg = ProgressDialog.show(MidForm.this, "", 
		        	    "Getting your location", true, true);
		        dlg.setCancelable(true);
		        
		        
		        
			}
		});
      
     
        

     
	
	  //gps
        
        
        
          sp3=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> 
        adp4=ArrayAdapter.createFromResource(this,R.array.Location, android.R.layout.simple_list_item_1);

                adp4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp3.setAdapter(adp4);
        sp3.setOnItemSelectedListener(new OnItemSelectedListener()
                {

                
                     public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                      // TODO Auto-generated method stub
                    ss1=sp3.getSelectedItem().toString();
                   Toast.makeText(getBaseContext(),ss1 , Toast.LENGTH_SHORT).show();
                    
                 	 
                   
                   
               
              }   

               
              public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub

                    }

                });
        
        
         sp1=(Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> 
        adp1=ArrayAdapter.createFromResource(this,R.array.types_of_shops, android.R.layout.simple_list_item_1);

                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp1.setAdapter(adp1);
        sp1.setOnItemSelectedListener(new OnItemSelectedListener()
                {

                
                     public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                      // TODO Auto-generated method stub
                    ss2=sp1.getSelectedItem().toString();
                   Toast.makeText(getBaseContext(),ss2 , Toast.LENGTH_SHORT).show();
              }   

               
              public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub

                    }

                });


}
    
    
    
    
    public void onClick(View arg0) {
			
		//dialog.show();
		//(new GetListTask()).execute((Object)null);
    	
    	//Intent intent=new Intent(LoginActivity.this,SecondActivity.class);
    	// intent.putExtra("CODE",1);
    	// startActivity(intent);
    	// finish();
    	
    	
    	Intent intent = new Intent(MidForm.this,List.class);
    	
       // Bundle b = new Bundle();
        //b.putString("ss1",ss1);
//ss2 = new Utils().getUsername(this);
       // i.putExtras(b);
    	ss3 = sp3.getSelectedItem().toString();
    	ss4 = sp1.getSelectedItem().toString();
    	String[] myStrings = new String[] {ss3, ss4};
    	intent.putExtra("strings", myStrings);
    	//intent.putExtra("ss3",ss3);
        startActivity(intent);
		
	}
    
    
    
    public class MyLocationListener implements LocationListener
    {

      public void onLocationChanged(Location loc)
      {

        lt=loc.getLatitude();
        lng=loc.getLongitude();
        
        l=new Double(lt).toString();
        ln=new Double(lng).toString();
        
        Text = "My current location is: " +" "+
        "Latitude = " + loc.getLatitude() +" "+
        "Longitude = " + loc.getLongitude(); 
        
        
        
       // Geocoder gcd = new Geocoder(getApplicationContext(),Locale.getDefault());
        
        
        
        
        
        
        lat.setText("You are in ");
        //lat.append(ln);
        try{
        	java.util.List<Address> addresses = geo.getFromLocation(loc.getLatitude(),loc.getLongitude(), 1); 
    		if(addresses.size()>0)
    			location= addresses.get(0).getLocality();
    			lat.append(location);
    			lat.setText(location);
    	} catch(IOException e){
    		e.printStackTrace();
    	}
        Toast.makeText( getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
        dlg.dismiss();
       
      }

      public void onProviderDisabled(String provider)
      {
        Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
      }

      public void onProviderEnabled(String provider)
      {
        Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
      }

      public void onStatusChanged(String provider, int status, Bundle extras)
      {

      }
       	
	 	//gps
    }

    
    
    
    
    
    /*
    
    
    private class GetListTask extends AsyncTask
    {		
            /**
             * Let's make the http request and return the result as a String.
             
            protected String doInBackground(Object... args)
            {                       
                    return ServerInterface.getList(ss1);
                  //  ,ss2
            }
    
    
            protected void onPostExecute(Object objResult)
            {
                     // check to make sure we're dealing with a string
                    if(objResult != null && objResult instanceof String) 
                    {                          
                            String result = (String) objResult;
                            if(result.contains("true"))
                            {
                            	startActivity(new Intent(MidForm.this, List.class));
                            }
                            else {
                            	
                            }
                    }
                    Toast.makeText(MidForm.this, "Getting List", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    
                  Intent i = new Intent(getApplicationContext(), List.class);
    				startActivity(i);
 
                    
                    
                    
                    
                    
                    
                    
                    
                    
	}}*/}
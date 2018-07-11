package com.project.LBDC;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class List extends ListActivity{
	
	public String ss1;
	public String ss2;
	
	public ArrayList<String> responselist = new ArrayList<String>();
	
	
	
	// Instance Variables
	
	
	private ProgressDialog dialog;
    private List mainActivity = null;
    
 

    /** Called when the activity is first created. */
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getListView();
            // we're going to need this later by the other threads
            mainActivity = this;
            //and ss2='".$ss2."'
         
          
            String[] myStrings = getIntent().getStringArrayExtra("strings");
            ss1 = myStrings[0];
            ss2 = myStrings[1];
            //  ss1 = new Utils().getSpin1(List.this);
           // Toast.makeText(List.this, ss1, Toast.LENGTH_SHORT).show();
            //dialog.dismiss();
            
         //   ss2 = new Utils().getSpin2(List.this);
          //  Toast.makeText(List.this, ss2, Toast.LENGTH_SHORT).show();
            //dialog.dismiss();
            
            System.out.println(ss1);
            System.out.println(ss2);
            
            // let's initialize the list, the real data will be filled in later
            String[] initialList = {""};

            // now we'll supply the data structure needed by this ListActivity
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.allshops, initialList);
            this.setListAdapter(adapter);
            ListView lv = this.getListView();
         lv.setOnItemClickListener(new OnItemClickListener() {
             
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	   
                    Intent i = new Intent(List.this, ShopDetails.class);
                    i.putExtra("address", ((TextView)view).getText().toString());
                    startActivity(i);
                }
             
            });
            
            /* Let's set up an item click listener to retrieve the animal sound and
             * display it to the user as a Toast.
             */
            
            dialog = new ProgressDialog(this);
            dialog.setCancelable(true);
            dialog.setMessage("Downloading all shops list...");
            dialog.show();
            (new GetListTask()).execute((Object)null);
    }
 
    /**
     * Used to implement an asynchronous retrieval of the list from the web.
     * This uses the AsyncTask class as a starting point. For more info, see
     * http://android-developers.blogspot.com/2009/05/painless-threading.html.
     */
    @SuppressWarnings("unchecked")
    private class GetListTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
            	return ServerInterface.getList(ss1,ss2);
            	//,new Utils().getSpin2(List.this)
            }

            
            protected void onPostExecute(Object objResult) {
            	 // check to make sure we're dealing with a string
                if(objResult != null && objResult instanceof String) {                          
                        String result = (String) objResult;
                        // this is used to hold the string array, after tokenizing
                        String[] responseList;

                        // we'll use a string tokenizer, with "." (fullstop) as the delimiter
                        StringTokenizer tk = new StringTokenizer(result, ",");

                        // now we know how long the string array is
                        responseList = new String[tk.countTokens()];

                        // let's build the string array
                        int i = 0;
                        while(tk.hasMoreTokens()) {
                                responselist.addAll(Arrays.asList(tk.nextToken()));
                        }
                        responselist.remove(responselist.size()-1);
                        // now we'll supply the data structure needed by this ListActivity
                        ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(mainActivity, R.layout.allshops, responselist);
                        mainActivity.setListAdapter(newAdapter);
                    }
                    dialog.dismiss();
            }

    }

}
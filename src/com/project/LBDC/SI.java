package com.project.LBDC;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

import android.app.ProgressDialog;
import android.widget.Toast;

public class SI {
	
    public static final String SERVER_URL = "http://lcation.uphero.com/test.php";

    
   
    
   
 
    
  
  
  public static String getInformation( String address) {
  	
      String data = "command=" + URLEncoder.encode("getInfo");
      data += "&address=" + address;
      return executeHttpRequest(data);
  }
    
    
    
    
   
    private static String executeHttpRequest(String data) {
            String result = "";
            try {
           System.out.println("itz in");
        
                    URL url = new URL(SERVER_URL);
                    URLConnection connection = url.openConnection();
                    
                    /*
                     * We need to make sure we specify that we want to provide input and
                     * get output from this connection. We also want to disable caching,
                     * so that we get the most up-to-date result. And, we need to 
                     * specify the correct content type for our data.
                     */
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    // Send the POST data
                    DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
                    dataOut.writeBytes(data);
                    dataOut.flush();
                    dataOut.close();

                    // get the response from the server and store it in result
                    DataInputStream dataIn = new DataInputStream(connection.getInputStream()); 
                    String inputLine;
                    while ((inputLine = dataIn.readLine()) != null) {
                            result += inputLine;
                    }
                    dataIn.close();
            } catch (IOException e) {
                    /*
                     * In case of an error, we're going to return a null String. This
                     * can be changed to a specific error message format if the client
                     * wants to do some error handling. For our simple app, we're just
                     * going to use the null to communicate a general error in
                     * retrieving the data.
                     */
                    e.printStackTrace();
                    result = null;
            }

            return result;
    }

}



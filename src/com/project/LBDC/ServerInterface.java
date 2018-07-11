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

public class ServerInterface {
	
    public static final String SERVER_URL = "http://lcation.uphero.com/locquerry.php";

    
    public static String getRegister(String name, String age, String phone, String email, String password) {
        
        String data = "command=" + URLEncoder.encode("register");
        data += "&name=" + name;
        data += "&age=" + age;
        data += "&phone=" + phone;
        data += "&email=" + email;
        data += "&password=" + password;
        return executeHttpRequest(data);
    }
    
    public static String getList( String ss1, String ss2) {
    	
        String data = "command=" + URLEncoder.encode("getList");
        data += "&ss1=" + ss1;
        data += "&ss2=" + ss2;
       // data += "&message=" + URLEncoder.encode(message);
        return executeHttpRequest(data);
    }
    
    public static String getLogin(String email, String password) {
         
            String data = "command=" + URLEncoder.encode("login");
            data += "&email=" + email;
            data += "&password=" + password;
            return executeHttpRequest(data);
    }
    
    public static String logout(String email, String password) {
    	
        String data = "command=" + URLEncoder.encode("logout");
        data += "&email=" + email;
        return executeHttpRequest(data);
    }
    
    public static String getFriendsList(String Email) {
    	
        String data = "command=" + URLEncoder.encode("getFriends");
        data += "&Email=" + Email;
        return executeHttpRequest(data);
    }
    
    public static String getPeopleList() {
    	
        String data = "command=" + URLEncoder.encode("getPeople");
        return executeHttpRequest(data);
    }
    
    public static String submitLocation(String message) {
        
        return executeHttpRequest(message);
    }
    
    
    
  public static String getInfo(String address) {
    	
    	String data = "command=" + URLEncoder.encode("getInfo");
        data += "&address=" + address;
        return executeHttpRequest(data);
    }
  
  public static String getInformation( String address) {
  	
      String data = "command=" + URLEncoder.encode("getInfo");
      //data += "&address=" + "Varsova jamnagar";      
      data += "&address=" + address;
      return executeHttpRequest(data);
  }
    
    
    
    
    
    public static String getPersonInfo(String username) {
    	
    	String data = "command=" + URLEncoder.encode("getPersonInfo");
        data += "&username=" + username;
        return executeHttpRequest(data);
    }
    
    public static String sendMessage(String username, String friendname, String message) {
    	
        String data = "command=" + URLEncoder.encode("sendMessage");
        data += "&username=" + username;
        data += "&friendname=" + friendname;
        data += "&message=" + URLEncoder.encode(message);
        return executeHttpRequest(data);
    }
    
    public static String getMessage(String username) {
    	
        String data = "command=" + URLEncoder.encode("getMessage");
        data += "&username=" + username;
        return URLDecoder.decode(executeHttpRequest(data));
    }
    
    public static String getUnreadMessageCount(String username) {
    	
        String data = "command=" + URLEncoder.encode("getUnreadMessageCount");
        data += "&username=" + username;
        return URLDecoder.decode(executeHttpRequest(data));
    }
    
    public static String getRequests(String username) {
    	
        String data = "command=" + URLEncoder.encode("getRequest");
        data += "&username=" + username;
        return executeHttpRequest(data);
    }
    
    public static String approveFriend(String username, String friendname) {
    	
        String data = "command=" + URLEncoder.encode("approveFriend");
        data += "&username=" + username;
        data += "&friendname=" + friendname;
        return executeHttpRequest(data);
    }
    
    public static String addFriend(String username, String friendname) {
    	
        String data = "command=" + URLEncoder.encode("addFriend");
        data += "&username=" + username;
        data += "&friendname=" + friendname;
        return executeHttpRequest(data);
    }
    
    public static String getBrands() {
        String data = "command=" + URLEncoder.encode("getBrand");
        return executeHttpRequest(data);
    }
    
    public static String getBrandUsers(String username, String brand) {
    	String data = "command=" + URLEncoder.encode("addBrand");
    	data += "&username=" + username;
        data += "&brand=" + brand;
        return executeHttpRequest(data);
    }
    
    public static String deleteAccount(String email) {
    	String data = "command=" + URLEncoder.encode("deleteAccount");
    	data += "&email=" + email;
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



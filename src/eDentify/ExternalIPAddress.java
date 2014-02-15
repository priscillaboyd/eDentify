package eDentify;

import java.io.*;
import java.net.*;

/* @author: Priscilla Nagashima Boyd (SRN 101007089)
 * 
 * MyIPAddress.java
 * 
 * Description: This class processes the user's external IP address
 * 				by using the web service provided www.amazonaws.com.
 * 				This is required for usage with the Google Search API.
 */

public class ExternalIPAddress {
	
	public static String getIPAddress(){
		
		URL ExternalIPURL = null;
		
		//Define URL to retrieve user's external IP address
		try {
			ExternalIPURL = new URL("http://checkip.amazonaws.com");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
        BufferedReader in = null; //initialise BufferedReader to get the info
        
        String ip = "";
        
        try {
            in = new BufferedReader(new InputStreamReader(ExternalIPURL.openStream()));
            ip = in.readLine();
            //return ip;
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return ip;
	}
	
}

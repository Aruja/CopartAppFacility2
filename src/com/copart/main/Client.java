package com.copart.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;


public class Client {
	
	
	public static String getDistance(String zipCode1,String zipCode2) throws org.json.simple.parser.ParseException
	{
		
		try {
		 	String clientKey="SH8Be5Im18StbtqknXgm9a9aoJZsukum2CXhHGDJOvd0ZDknBAJKLSV9CVC6g6si";
			URL url = new URL("https://www.zipcodeapi.com/rest/SH8Be5Im18StbtqknXgm9a9aoJZsukum2CXhHGDJOvd0ZDknBAJKLSV9CVC6g6si/distance.json/"+zipCode1.trim()+"/"+zipCode2.trim()+"/km");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				System.out.println(url.toString());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String jsonData="";
			String line;
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
			
			
			try {
				
				JSONObject jsonObject = new JSONObject(jsonData);
				Double distance=jsonObject.getDouble("distance");
				return distance+"";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {
			e.printStackTrace();
		  }
		return "";
		
	}

}

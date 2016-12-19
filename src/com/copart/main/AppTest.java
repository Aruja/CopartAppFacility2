package com.copart.main;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.copart.utility.Utilities;

public class AppTest {
	
	public static void main(String args[])
	{
		
		Map<String,String> facilitiesMap=Utilities.parseCSV();
		Map<String,String> customerMap=Utilities.parseCustomerCSV();
		String customerId="12345";
		String zipCode="75252";
		
		try {
			String appFacility=getAppropriateFacility(customerId,zipCode,customerMap,facilitiesMap);
			System.out.println("Nearest Facility for Customer Id: "+customerId+" with zipcode "+zipCode+" is "+appFacility);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static String getAppropriateFacility(String customerid,String zipCode,Map<String,String> customerMap,Map<String,String> facilitiesMap) throws ParseException
	{
		
		if(customerMap.containsKey(customerid))
		{
			return customerMap.get(customerid);
		}
		else
		{
			return getNearestFacility(zipCode, facilitiesMap);
		}
		
	}
	
	
	
	public static String getNearestFacility(String zipcode,Map<String,String> facilitiesMap) throws ParseException
	{
		String facility="";
		Double MINDISTANCE=Double.MAX_VALUE;
		for (String key : facilitiesMap.keySet()) {
		   
			String distance=Client.getDistance(zipcode,key);
			Double sdistance=Double.parseDouble(distance);
			if(sdistance<=MINDISTANCE){
				facility=facilitiesMap.get(key);
			}
		}
		
		return facility;
	}
	
	/*public static void addCustomer(String custId,String zipcode,String facility)
	{
		
		String cust=custId+"\t"+zipcode+"\t"+facility;
		FileWriter pw = new FileWriter("data/customer.csv");
        Iterator s = customerIterator();
        if (s.hasNext()==false){
            System.out.println("Empty");
        }
        while(s.hasNext()){
            Customer current  = (Customer) s.next();
            System.out.println(current.toString()+"\n");
            pw.append(current.getName());
            pw.append(",");
            pw.append(current.getAddress());
            pw.append("\n");
        }
            pw.flush();
            pw.close();
		
		
		
		
		
		
	}*/
	

}

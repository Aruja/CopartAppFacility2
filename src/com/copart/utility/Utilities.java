package com.copart.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utilities {
	
	public static  Map<String, String> parseCustomerCSV()
	{
		
		String csvFile = "data/customer.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\t";
        Map<String,String> customer=new HashMap<String,String>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                //use comma as separator
                String[] custData = line.split(cvsSplitBy);
                String custId=custData[0];
                String zipCode=custData[1];
                String nearestFacility=custData[2];
                customer.put(custId,nearestFacility);
               
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(customer.size());
        return customer;
    }
	
	public static Map<String, String> parseCSV() {

		String csvFile = "data/data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";
		Map<String, String> copartFacilities = new HashMap<String, String>();
		try {

			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] zipData = line.split(cvsSplitBy);
				String zipCode = zipData[4];
				String location = zipData[2] + "," + zipData[1];
				if(zipCode!=null && isInteger(zipCode) & zipCode!=null && checkIntegerLength(zipCode))
					copartFacilities.put(zipCode, location);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return copartFacilities;
	}
	
	
	
	public static boolean isInteger(String zip)
	{
		
		 try
		  { int i = Integer.parseInt(zip); return true; }

		 catch(NumberFormatException er)
		  { return false; }

	}
	
	
	public static boolean checkIntegerLength(String zip)
	{
		
		 if(zip.length()!=5)
			 return false;
		 else
			 return true;
	}
	
}

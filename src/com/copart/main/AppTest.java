package com.copart.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.copart.utility.Utilities;

public class AppTest {

	public static void main(String args[]) throws IOException {

		Map<String, String> facilitiesMap = Utilities.parseCSV();
		Map<String, String> customerMap = Utilities.parseCustomerCSV();

		String file = "data/input.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		String data = null;
		try {
			data = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String custData[] = data.split(",");
		String customerId = custData[0];
		String zipCode = custData[1];

		try {
			String appFacility = getAppropriateFacility(customerId, zipCode,
					customerMap, facilitiesMap);
			System.out.println("Nearest Facility for Customer Id: "
					+ customerId + " with zipcode " + zipCode + " is "
					+ appFacility);

			if (!customerMap.containsKey(customerId)) {
				addCustomer(customerId, zipCode, appFacility);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getAppropriateFacility(String customerid,
			String zipCode, Map<String, String> customerMap,
			Map<String, String> facilitiesMap) throws ParseException {

		if (customerMap.containsKey(customerid)) {
			return customerMap.get(customerid);
		} else {
			return getNearestFacility(zipCode, facilitiesMap);
		}

	}

	public static String getNearestFacility(String zipcode,
			Map<String, String> facilitiesMap) throws ParseException {
		String facility = "";
		Double MINDISTANCE = Double.MAX_VALUE;
		for (String key : facilitiesMap.keySet()) {

			String distance = Client.getDistance(zipcode, key);
			Double sdistance = Double.parseDouble(distance);
			if (sdistance <= MINDISTANCE) {
				facility = facilitiesMap.get(key);
			}
		}

		return facility;
	}

	public static void addCustomer(String custId, String zipcode,
			String facility) throws IOException {
		String cust = "\n" + custId + "\t" + zipcode + "\t" + facility;

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			String data = " This is new content";
			File file = new File("data/customer.csv");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(cust);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}

}

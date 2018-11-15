package com.cg.wps.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.cg.wps.dto.Credentials;
import com.cg.wps.dto.Customer;

public class DBUtil {
	static Random rand = new Random();
	public static HashMap<String, Customer> bankDBMap = new HashMap<>();
	public static HashMap<String, String> credentialDBMap = new HashMap<>();
	static {
		credentialDBMap.put("162221", "ay123");
		bankDBMap.put(
				"162221",
				new Customer("162221", "Ayushmaan Gaur", LocalDate.of(1996,
						Month.MAY, 24), "Saving", 15500.0F, 200000.0));
		credentialDBMap.put("160662", "di123");
		bankDBMap.put("160662", new Customer("160662", "Dinesh Nandi",
				LocalDate.of(2018, Month.SEPTEMBER, 26), "Current", 15500.0F,
				200000.0));
		credentialDBMap.put("160601", "ab123");
		bankDBMap.put("160601", new Customer("160601", "Abhimanu Sharma",
				LocalDate.of(2018, Month.SEPTEMBER, 14), "Saving", 15500.0F,
				200000.0));
		credentialDBMap.put("160608", "ra123");
		bankDBMap.put(
				"160608",
				new Customer("160608", "Ravi Tandon", LocalDate.of(2017,
						Month.DECEMBER, 15), "Current", 15500.0F, 200000.0));
	}

	public static String newCustomer(String password, Customer customer) {
		credentialDBMap.put(customer.getAccNumber(), password);
		bankDBMap.put(customer.getAccNumber(), customer);
		return customer.getAccNumber();
	}

	public static HashMap<String, String> getAllCustCred() {
		return credentialDBMap;
	}

	public static HashMap<String, Customer> getAllCustomer() {
		return bankDBMap;
	}

}

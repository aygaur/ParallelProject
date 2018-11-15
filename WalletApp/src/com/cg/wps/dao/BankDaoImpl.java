package com.cg.wps.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.cg.wps.dto.Credentials;
import com.cg.wps.dto.Customer;
import com.cg.wps.util.DBUtil;

public class BankDaoImpl implements BankDao {
	static Random rand = new Random();

	@Override
	public Customer signIn(String cstId, String cstPass) {
		// TODO Auto-generated method stub
		Credentials credential = null;
		Customer customer = null;
		HashMap<String, String> credDBMap = DBUtil.getAllCustCred();
		HashMap<String, Customer> bankDBMap = DBUtil.getAllCustomer();
		Iterator<String> itKey = credDBMap.keySet().iterator();
		Iterator<String> itPass = credDBMap.values().iterator();
		Iterator<Customer> it = bankDBMap.values().iterator();
		while (itKey.hasNext()) {
			credential = new Credentials(itKey.next(), itPass.next());
			customer = it.next();
			if (credential.getAccNumber().equals(cstId)
					&& credential.getPassword().equals(cstPass)) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public void deposit(int depositAmount, Customer customer) {
		// TODO Auto-generated method stub
		customer.setBalance(customer.getBalance() + depositAmount);

	}

	@Override
	public void withdraw(int withdrawAmount, Customer customer) {
		// TODO Auto-generated method stub
		customer.setBalance(customer.getBalance() - withdrawAmount);

	}

	@Override
	public Customer transfer(int transferAmount, Customer customer,
			String reciever) {
		// TODO Auto-generated method stub
		Customer transReciever = null;
		HashMap<String, Customer> bankDBMap = DBUtil.getAllCustomer();
		Iterator<Customer> it = bankDBMap.values().iterator();
		while (it.hasNext()) {
			transReciever = it.next();
			if (transReciever.getAccNumber().equals(reciever)) {
				return transReciever;
			}
		}
		return transReciever;
	}

	@Override
	public String createAccount(String password, String custName, String accType) {
		// TODO Auto-generated method stub
		return DBUtil.newCustomer(password,
				new Customer(Integer.toString(rand.nextInt(9000) + 1), custName,
						LocalDate.now(), accType, 0.0F, 200000.0));
	}
}

package com.cg.wps.service;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import com.cg.wps.dao.BankDao;
import com.cg.wps.dao.BankDaoImpl;
import com.cg.wps.dto.Credentials;
import com.cg.wps.dto.Customer;
import com.cg.wps.dto.Transactions;
import com.cg.wps.exception.TransactionException;

public class BankServiceImpl implements BankService {
	BankDao bankDao = null;
	static Random rand = new Random();

	public BankServiceImpl() {
		// TODO Auto-generated constructor stub
		bankDao = new BankDaoImpl();
	}

	@Override
	public Customer signIn(String cstId, String cstPass) {
		// TODO Auto-generated method stub
		return bankDao.signIn(cstId, cstPass);
	}

	@Override
	public void deposit(BigInteger depositAmount, Customer customer) {
		// TODO Auto-generated method stub
		bankDao.deposit(depositAmount, customer);

	}

	@Override
	public void withdraw(BigInteger withdrawAmount, Customer customer) {
		// TODO Auto-generated method stub
		bankDao.withdraw(withdrawAmount, customer);

	}

	@Override
	public void transfer(BigInteger transferAmount, Customer customer,
			String reciever) {
		// TODO Auto-generated method stub
		Customer transReciever = bankDao.transfer(transferAmount, customer,
				reciever);
		bankDao.withdraw(transferAmount, customer);
		bankDao.deposit(transferAmount, transReciever);

	}

	@Override
	public boolean validateWithdraw(String withdrawAmount, Customer customer)
			throws TransactionException {
		// TODO Auto-generated method stub
		String amountPattern = "[0-9]*";
		if (Pattern.matches(amountPattern, withdrawAmount)) {
			BigInteger widrawAm = new BigInteger(withdrawAmount);
			int validate = widrawAm.compareTo(customer.getBalance());
			
			if ((validate == 0)||(validate == -1)) {
				return true;
			} else {
				throw new TransactionException("Insuficient Balance");
			}
		} else {
			throw new TransactionException("Amount must be in number format");
		}
	}

	@Override
	public boolean validateEnteredAmount(String amount)
			throws TransactionException {
		String amountPattern = "[0-9]*";
		if (Pattern.matches(amountPattern, amount)) {
			return true;
		} else {
			throw new TransactionException("Amount must be in number format");
		}
	}

	@Override
	public boolean validateNumber(String choose) throws TransactionException {
		// TODO Auto-generated method stub
		String chooseNum = "[0-9]*";
		if (Pattern.matches(chooseNum, choose)) {
			return true;
		} else {
			throw new TransactionException(
					"Entered choice must be in number format");
		}
	}

	@Override
	public boolean validateName(String name) throws TransactionException {
		String namePatter = "[A-Z][a-z]+" + " " + "[A-Z][a-z]+";

		if (Pattern.matches(namePatter, name)) {
			return true;
		} else {
			throw new TransactionException(" Invalid input "
					+ " Only Char are  allowed  and should start"
					+ " with Capital ex.    Ayushmaan");
		}
	}

	@Override
	public String createAccount(String password, String custName,
			String accType, String date) {
		// TODO Auto-generated method stub

		String AccNumber = Integer.toString(rand.nextInt(500000) + 1);
		Credentials credentials = new Credentials(AccNumber, password);
		Customer customer = new Customer(AccNumber, custName, date, accType,
				new BigInteger("0"), new BigInteger("200000"));
		bankDao.createAccount(credentials, customer);
		return customer.getaccNum();
	}

	@Override
	public List<Transactions> fetchAllTransaction(Customer customer) {
		// TODO Auto-generated method stub
		return bankDao.fetchAllTransaction(customer);
	}
}

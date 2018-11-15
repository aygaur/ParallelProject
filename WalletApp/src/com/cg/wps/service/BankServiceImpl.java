package com.cg.wps.service;

import java.util.regex.Pattern;

import com.cg.wps.dao.BankDao;
import com.cg.wps.dao.BankDaoImpl;
import com.cg.wps.dto.Credentials;
import com.cg.wps.dto.Customer;
import com.cg.wps.exception.TransactionException;

public class BankServiceImpl implements BankService {
	BankDao bankDao = null;

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
	public void deposit(int depositAmount, Customer customer) {
		// TODO Auto-generated method stub
		bankDao.deposit(depositAmount, customer);

	}

	@Override
	public void withdraw(int withdrawAmount, Customer customer) {
		// TODO Auto-generated method stub
		bankDao.withdraw(withdrawAmount, customer);

	}

	@Override
	public void transfer(int transferAmount, Customer customer, String reciever) {
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
			if (Integer.parseInt(withdrawAmount) <= customer.getBalance()) {
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
	public String createAccount(String password, String custName, String accType) {
		// TODO Auto-generated method stub
		return bankDao.createAccount(password, custName, accType);
	}
}

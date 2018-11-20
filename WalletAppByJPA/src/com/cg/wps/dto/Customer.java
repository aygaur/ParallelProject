package com.cg.wps.dto;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private String accNum;

	public Customer(String accNum, String accHolderName, String custDOB,
			String accType, BigInteger balance, BigInteger withDrawLimit) {
		super();
		this.accNum = accNum;
		this.accHolderName = accHolderName;
		this.custDOB = custDOB;
		this.accType = accType;
		this.balance = balance;
		this.withDrawLimit = withDrawLimit;
	}

	private String accHolderName;
	private String custDOB;
	private String accType;
	private BigInteger balance;
	private BigInteger withDrawLimit;

	public String getaccNum() {
		return accNum;
	}

	public void setaccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public String getCustDOB() {
		return custDOB;
	}

	public void setCustDOB(String custDOB) {
		this.custDOB = custDOB;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public BigInteger getBalance() {
		return balance;
	}

	public void setBalance(BigInteger balance) {
		this.balance = balance;
	}

	public BigInteger getWithDrawLimit() {
		return withDrawLimit;
	}

	public void setWithDrawLimit(BigInteger withDrawLimit) {
		this.withDrawLimit = withDrawLimit;
	}

	@Override
	public String toString() {
		return "Customer [accNumber=" + accNum + ", accHolderName="
				+ accHolderName + ", custDOB=" + custDOB + ", accType="
				+ accType + ", balance=" + balance + ", withDrawLimit="
				+ withDrawLimit + "]";
	}

}

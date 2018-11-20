package com.cg.wps.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transactions {
	@Id
	private String transId;

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(String transId, String accNum, String transType,
			BigInteger amount) {
		super();
		this.transId = transId;
		this.accNum = accNum;
		this.transType = transType;
		this.amount = amount;
	}

	private String accNum;
	private String transType;
	private BigInteger amount;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transactions [transId=" + transId + ", accNum=" + accNum
				+ ", transType=" + transType + ", amount=" + amount + "]";
	}

}

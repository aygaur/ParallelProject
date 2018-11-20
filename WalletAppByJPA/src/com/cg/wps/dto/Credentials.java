package com.cg.wps.dto;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Credentials{
	@Id
	private String accNum;
	private String password;
	public Credentials() {
		super();
	}

	public Credentials(String accNum, String password) {
	
		this.accNum = accNum;
		this.password = password;
	}

	

	public String getaccNum() {
		return accNum;
	}

	public void setaccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Credentials [accNumber=" + accNum + ", password=" + password
				+ "]";
	}

}

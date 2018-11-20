package com.cg.wps.junit;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.wps.dao.BankDaoImpl;
import com.cg.wps.dto.Credentials;
import com.cg.wps.dto.Customer;

public class WalletTest {
	static Random rand = new Random();
	static BankDaoImpl bankDao = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bankDao = new BankDaoImpl();
		System.out.println("...Start Class...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("...End Class...");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("...Test Function Start...");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("...Test Function End...");
	}

	@Test
	public void testSignIn() {
		Assert.assertNotNull(bankDao.signIn("92150", "vg123"));
	}

	@Test
	public void testFetchAllTransactions() {
		Assert.assertNotNull(bankDao.fetchAllTransaction(new Customer("92150",
				"Vijay Ghosh", "95-06-23", "Saving", new BigInteger("0"),
				new BigInteger("200000"))));
	}

}

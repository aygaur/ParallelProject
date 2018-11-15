package com.cg.wps.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.wps.dao.BankDaoImpl;

public class WalletTest {
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
		Assert.assertNotNull(bankDao.signIn("162221", "ay123"));
	}

	@Test
	public void testCreateAcc() {
		Assert.assertNotNull(bankDao.createAccount("ay123", "Ayushmaan",
				"Current"));
	}

	@Test
	public void testFundTransfer() {
		Assert.assertNotNull(bankDao.transfer(2000,
				bankDao.signIn("162221", "ay123"), "160608"));
	}

}

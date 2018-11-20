package com.cg.wps.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;

import com.cg.wps.dto.Credentials;
import com.cg.wps.dto.Customer;
import com.cg.wps.dto.Transactions;

public class BankDaoImpl implements BankDao {
	static Random rand = new Random();

	@Override
	public Customer signIn(String accNum, String cstPass) {
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("JPA-PU");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Credentials credentials = entitymanager.find(Credentials.class, accNum);
		// System.out.println(credentials);
		if (credentials.getPassword().equals(cstPass)) {
			Customer customer = entitymanager.find(Customer.class, accNum);
			entitymanager.close();
			emfactory.close();
			return customer;
		} else {
			return null;
		}

	}

	@Override
	public void deposit(BigInteger depositAmount, Customer customer) {
		// TODO Auto-generated method stub
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("JPA-PU");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Customer customer2 = entitymanager.find(Customer.class,
				customer.getaccNum());
		customer2.setBalance(customer2.getBalance().add(depositAmount));
		String transId = Integer.toString(rand.nextInt(5000000) + 1);
		Transactions trans = new Transactions(transId, customer.getaccNum(),
				"Deposit", depositAmount);
		entitymanager.merge(customer2);
		entitymanager.merge(trans);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@Override
	public void withdraw(BigInteger withdrawAmount, Customer customer) {
		// TODO Auto-generated method stub
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("JPA-PU");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		String transId = Integer.toString(rand.nextInt(5000000) + 1);
		Transactions trans = new Transactions(transId, customer.getaccNum(),
				"Withdraw", withdrawAmount);
		customer.setBalance(customer.getBalance().subtract(withdrawAmount));
		entitymanager.merge(customer);
		entitymanager.merge(trans);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@Override
	public Customer transfer(BigInteger transferAmount, Customer customer,
			String reciever) {
		// TODO Auto-generated method stub
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("JPA-PU");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Customer transReciever = entitymanager.find(Customer.class, reciever);

		entitymanager.close();
		emfactory.close();
		return transReciever;
	}

	@Override
	public void createAccount(Credentials credentials, Customer customer) {
		// TODO Auto-generated method stub
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("JPA-PU");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.merge(credentials);
		entitymanager.merge(customer);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();

	}

	@Override
	public List<Transactions> fetchAllTransaction(Customer customer) {
		// TODO Auto-generated method stub
		EntityManagerFactory emfactory = Persistence
				.createEntityManagerFactory("JPA-PU");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String accNum = customer.getaccNum();
		Query query = entitymanager
				.createQuery("from Transactions where accNum=?");
		query.setParameter(1, accNum);
		List<Transactions> transList = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return transList;
	}
}

package com.cg.wps.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.wps.dto.Credentials;
import com.cg.wps.dto.Customer;
import com.cg.wps.dto.Transactions;
import com.cg.wps.exception.TransactionException;
import com.cg.wps.service.BankService;
import com.cg.wps.service.BankServiceImpl;

/***
 * 
 * @author aygaur To Know the fuctionality Please refer WalletAppDoc
 *
 */
public class MainUI {
	static Scanner sc = new Scanner(System.in);
	static Random rand = new Random();
	static BankService bankSer = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bankSer = new BankServiceImpl();
		String choice;
		while (true) {
			System.out.println("\nWelcome to Wallet App for XYZ Bank");
			System.out.println("1. Create New Account");
			System.out.println("2. SignIn");
			choice = sc.next();
			try {
				if (bankSer.validateNumber(choice)) {
					switch (Integer.parseInt(choice)) {
					case 1:
						createAccount();
						break;
					case 2:
						signIn();
						break;
					default:
						System.out.println("!!!Please Enter a valid choice!!!");
						System.out.println("\n...Thank You...");
					}
				}
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out
						.println("...Unsuccessful Transaction...\n...Thank You...");
			}
		}

	}

	private static void signIn() {
		// TODO Auto-generated method stub
		System.out.println("Enter customer Id");
		String cstId = sc.next();
		System.out.println("Enter Password");
		String cstPass = sc.next();
		// try {
		Customer customer = bankSer.signIn(cstId, cstPass);

		if (customer.getAccHolderName().equals(null)) {
			System.out.println("...Invalid Credentials...\n");
		} else {
			openAccount(customer);
		}

		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// System.out.println("...Invalid Credentials...\n...Thank You...");
		// }
	}

	private static void openAccount(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("...Hi " + customer.getAccHolderName()
				+ "...\n...Welcome to XYZ Bank...");
		System.out.println("Please enter a valid choice:\n"
				+ "1. Print Mini Statement\n" + "2. Show Balance\n"
				+ "3. Deposit\n" + "4. Withdraw\n" + "5. Fund Transfer");
		String choose = sc.next();
		try {
			if (bankSer.validateNumber(choose)) {
				switch (Integer.parseInt(choose)) {
				case 1:
					List<Transactions> trans = bankSer
							.fetchAllTransaction(customer);
					Iterator<Transactions> it = trans.iterator();

					System.out.println("\nTransaction Id "
							+ "\tAccount Number " + "\tTransaction Type "
							+ "\tAmount ");
					while (it.hasNext()) {
						Transactions miniTrans = it.next();

						System.out.println(miniTrans.getTransId() + "\t\t"
								+ miniTrans.getAccNum() + "\t\t"
								+ miniTrans.getTransType() + "\t\t"
								+ miniTrans.getAmount());

					}
					System.out
							.println("\n...Transaction Successful...\n...Thank You...");
					customer = null;
					break;

				case 2:
					System.out.println("... Rs. " + customer.getBalance()
							+ " ...");
					System.out
							.println("...Transaction Successful...\n...Thank You...");
					customer = null;
					break;

				case 3:
					System.out.println("Enter amount Rs.");
					String depositAmount = sc.next();
					try {
						if (bankSer.validateEnteredAmount(depositAmount)) {
							BigInteger depAm = new BigInteger(depositAmount);
							bankSer.deposit(depAm, customer);
							System.out
									.println("...Transaction Successful...\n...Thank You...");
						}
					} catch (TransactionException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						System.out
								.println("...Unsuccessful Transaction...\n...Thank You...");
					}
					customer = null;
					break;

				case 4:
					System.out.println("Enter amount");
					String withdrawAmount = sc.next();

					try {
						if (bankSer.validateWithdraw(withdrawAmount, customer)) {
							BigInteger withDrAm = new BigInteger(withdrawAmount);
							bankSer.withdraw(withDrAm, customer);
							System.out
									.println("...Transaction Successful...\n...Thank You...");
						}
					} catch (TransactionException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						System.out
								.println("...Unsuccessful Transaction...\n...Thank You...");
					}
					customer = null;
					break;

				case 5:
					System.out
							.println("Enter accountId in which money to be transfered");
					String reciever = sc.next();
					System.out.println("Enter amount");
					String transferAmount = sc.next();

					try {
						if (bankSer.validateWithdraw(transferAmount, customer)) {
							bankSer.transfer(new BigInteger(transferAmount),
									customer, reciever);
							System.out
									.println("...Transaction Successful...\n...Thank You...");
						}
					} catch (TransactionException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						System.out
								.println("...Unsuccessful Transaction...\n...Thank You...");
					}
					customer = null;
					break;

				default:
					System.out.println("!!!Please Enter Valid Choice!!!");
					System.out
							.println("...Unsuccessful Transaction...\n...Thank You...");
					customer = null;
					break;
				}
			}
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out
					.println("...Unsuccessful Transaction...\n...Thank You...");
		}
	}

	private static void createAccount() {
		// TODO Auto-generated method stub
		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				System.in));
		String accType = null;
		String date = null;
		String password = null;
		String confirmPassword = null;
		int limit = 3;
		System.out.println("Enter Name");
		String custName;
		try {
			custName = buffer.readLine();

			try {
				if (bankSer.validateName(custName)) {
					System.out
							.println("Select Account Type:\n1. Saving\n2. Current");
					String choice = sc.next();
					try {
						if (bankSer.validateNumber(choice)) {
							switch (Integer.parseInt(choice)) {
							case 1:
								accType = "Saving";
								break;
							case 2:
								accType = "Current";
								break;
							default:
								System.out
										.println("!!!Please Enter a Valid Choice!!!");
								System.out.println("\n...Thank You...");
							}
							while (limit > 0) {
								System.out.println("Enter password:");
								password = sc.next();
								System.out.println("Confirm password:");
								confirmPassword = sc.next();
								if (password.equals(confirmPassword)) {
									password = confirmPassword;
									break;
								} else {
									password = null;
									limit--;
									System.out
											.println("Password and Confirm password not matched.\nEnter Again");
									if (limit >= 1) {
									} else {
										break;
									}

								}
							}

							if (password == null) {
								System.out
										.println("!!!Number of Attempts exceeded!!!");
							} else {
								System.out
										.println("!!!Enter DOB in format(YY-MM-DD) !!!");
								date = sc.next();
								BankServiceImpl ser = new BankServiceImpl();
								String custAccNumber = ser.createAccount(
										password, custName, accType, date);
								System.out
										.println("...Account Successfully Created...\n"
												+ "Kindly take note of your account Id: "
												+ custAccNumber
												+ "\n...Thank You...");
							}
						}
					} catch (TransactionException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						System.out.println("\n...Thank You...");
					}
				}
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				System.out.println("\n...Thank You...");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

package com.example.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.assignment.Customer.AccountType;

@SpringBootApplication
public class BankingApplication {
	public static final int LENGTH = 12;
	public static HashMap<String,Customer> cutomerMap;	
	public static List<Counter> counters;
	public static void main(String[] args) {
		mockData();
		SpringApplication.run(BankingApplication.class, args);
	}	
	
	public static void mockData() {
		cutomerMap=new HashMap<String,Customer>();
		List<ServiceType> services = new ArrayList<>();
		List<Customer> custList = new ArrayList<>();
		services.add(ServiceType.DEPOSIT);
		services.add(ServiceType.WITHDRAWL);
		String accnt1 = generateRandom(LENGTH);
		Customer cust1 = new Customer(accnt1, "John Doe", 
				AccountType.regular, services);
		cutomerMap.put(accnt1, cust1);
		String accnt2 = generateRandom(LENGTH);
		Customer cust2 = new Customer(accnt1, "Arya Stark", 
				AccountType.premium, Arrays.asList(ServiceType.DEPOSIT));
		cutomerMap.put(accnt2, cust2);
		Customer cust3 = new Customer(accnt1, "ram", 
				AccountType.regular, services);
		Customer cust4 = new Customer(accnt1, "shyam", 
				AccountType.regular, services);
		Customer cust5 = new Customer(accnt1, "ram2", 
				AccountType.premium, services);
		counters = new ArrayList<>();
		Counter counterDepost1 = new Counter(1, ServiceType.DEPOSIT);
		Counter counterWithDrawl1 = new Counter(1, ServiceType.WITHDRAWL);
		counterWithDrawl1.setTokens(Arrays.asList(new Token(1, 0)));
		custList.add(cust3);
		custList.add(cust4);
		custList.add(cust5);
		List<Token> tokens = new ArrayList<>();
		tokens.add(new Token(2, 0));
		tokens.add(new Token(3, 1));
		Counter counterDepost2 = new Counter(2, ServiceType.DEPOSIT);
		//counterDepost2.setCustomers(custList);
		Counter counterDepost3 = new Counter(3, ServiceType.DEPOSIT);
		counterDepost3.setTokens(tokens);
		counters.add(counterDepost1);
		counters.add(counterWithDrawl1);
		counters.add(counterDepost2);
		counters.add(counterDepost3);
	}
	
	public static String generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return new String(digits);
	}
}

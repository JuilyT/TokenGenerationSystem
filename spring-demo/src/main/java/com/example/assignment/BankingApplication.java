package com.example.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankingApplication {
	public static final int LENGTH = 12;
	public static HashMap<String,Customer> customerMap;	
	public static List<Counter> counters;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CounterRepository counterRepository;
	
	public static void main(String[] args) {
		mockData();
		SpringApplication.run(BankingApplication.class, args);
	}	
	
	public static void mockData() {
		customerMap=new HashMap<String,Customer>();
		List<ServiceType> services = new ArrayList<>();
		services.add(ServiceType.DEPOSIT);
		services.add(ServiceType.WITHDRAWL);
		String accnt1 = generateRandom(LENGTH);
		Customer cust1 = new Customer(accnt1, "John Doe", 
				AccountType.REGULAR, services);
		customerMap.put(accnt1, cust1);
		String accnt2 = generateRandom(LENGTH);
		Customer cust2 = new Customer(accnt2, "Arya Stark", 
				AccountType.PREMIUM, Arrays.asList(ServiceType.DEPOSIT));
		customerMap.put(accnt2, cust2);
		String accnt3 = generateRandom(LENGTH);
		Customer cust3 = new Customer(accnt3, "ram", 
				AccountType.REGULAR, services);
		customerMap.put(accnt3, cust3);
		String accnt4= generateRandom(LENGTH);
		Customer cust4 = new Customer(accnt4, "shyam", 
				AccountType.REGULAR, services);
		customerMap.put(accnt4, cust4);
		String accnt5 = generateRandom(LENGTH);
		Customer cust5 = new Customer(accnt5, "ram2", 
				AccountType.PREMIUM, services);
		customerMap.put(accnt5, cust5);
		counters = new ArrayList<>();
		Counter counterDepost1 = new Counter(ServiceType.DEPOSIT);
		Counter counterWithDrawl1 = new Counter(ServiceType.WITHDRAWL);
		Counter counterDepost2 = new Counter(ServiceType.DEPOSIT);
		Counter counterDepost3 = new Counter(ServiceType.DEPOSIT);
		Counter counterAccountCreation1 = new Counter(ServiceType.ACCOUNT_CREATION);
		counters.add(counterDepost1);
		counters.add(counterWithDrawl1);
		counters.add(counterDepost2);
		counters.add(counterDepost3);
		counters.add(counterAccountCreation1);
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
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	for (Customer cust : customerMap.values()) {
	    		customerRepository.save(cust);
			}
	    	for (Counter countr : counters) {
	    		counterRepository.save(countr);
			}
	    };
	}
}

package com.example.assignment;

import org.springframework.beans.factory.annotation.Autowired;

public class BankingServiceImpl implements BankingService{
	public static final int LENGTH = 12;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CounterRepository counterRepository;
	
	
}

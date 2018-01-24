package com.example.assignment.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.assignment.TokenGenerator;
import com.example.assignment.enums.ServiceType;
import com.example.assignment.enums.Status;
import com.example.assignment.model.Counter;
import com.example.assignment.model.Customer;
import com.example.assignment.model.Token;
import com.example.assignment.repository.CounterRepository;
import com.example.assignment.repository.CustomerRepository;
import com.example.assignment.utility.Util;

public class BankingServiceImpl implements BankingService{
	public static final int LENGTH = 12;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BankingServiceImpl.class);
	@Autowired
	TokenGenerator tokenGenerator;
	@Autowired
	CounterOperator operator;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CounterRepository counterRepository;
	
	@Override
	public Map<String, Counter> getCounters() {
		 Map<String, Counter> dataMap = new LinkedHashMap<String, Counter>();
		 for (Counter countr : counterRepository.findAllByOrderByIdAsc()) {
			 dataMap.put(countr.toString(), countr);
		 }
		 return dataMap;
	}
	
	@Override
	public Map<String, Counter> addCounters() {
		List<Counter> counters= new ArrayList<>();
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
		Map<String, Counter> dataMap = new HashMap<String, Counter>();
		for (Counter countr : counters) {
			Counter result = (Counter) counterRepository.save(countr);
			dataMap.put(result.toString(), result);
		}
		return dataMap;
	}

	@Override
	public List<Counter> getServiceCounters(ServiceType type) {
		return counterRepository.findByServiceType(type);
	}
	
	@Override
	public void process(Customer customer) throws Exception {
		 if(customer.getId() == null) {
			 generateNewAccountToken(customer);
		 } 
		 tokenGenerator.generate(customer);
	 }
	 
	 public void generateNewAccountToken(Customer customer){
		 customerRepository.save(customer);
		 if (customer.getServices()==null) {
			List<ServiceType> services = new ArrayList<>();
			customer.setServiceType(services);
		 }
		 customer.getServices().add(ServiceType.ACCOUNT_CREATION);
	 }

	@Override
	public void operate(Token token) throws Exception {
		if (token.getStatus()==Status.CANCELLED || token.getStatus()==Status.COMPLETED) {
			throw new Exception("Not a valid token");
		}
    	operator.operate(token.getCounter());
	}
}

package com.example.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/api/banking")
public class Controller {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CounterRepository counterRepository;
	@Autowired
	TokenGenerator tokenGenerator;
		
	/**
	  * GET /counters  --> Create a new department and save it in the database.
	  */
	 @RequestMapping(value="/counters", method=RequestMethod.GET)
	 public Map<String, Counter> getCounters() {
		 Map<String, Counter> dataMap = new HashMap<String, Counter>();
		 for (Counter countr : BankingApplication.counters) {
			 dataMap.put(countr.toString(), countr);
		 }
		 return dataMap;
	 }
	 
	 /**
	  * POST /create  --> Create a new department and save it in the database.
	  */
	 @RequestMapping(value="/counters", method=RequestMethod.POST)
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
		/* List<Counter> result = counterRepository.save(counters);*/
		 /*if (customer.getId() == null) {
		      em.persist(customer);
		      return customer;
		    } else {
		      return em.merge(customer);
		    }*/
		 for (Counter countr : counters) {
			 Counter result = (Counter) counterRepository.save(countr);
			 dataMap.put(result.toString(), result);
		 }
		 return dataMap;
	 }
	 
	 @RequestMapping(value="/counters/type", method=RequestMethod.GET)
	 public List<Counter> getServiceCounters(@RequestParam ServiceType type) {
		 List<Counter> counters= counterRepository.findByServiceType(type);
		 return counters;
	 }

	 /*@RequestMapping(value="/token", method=RequestMethod.POST)
	 public Token createToken(@RequestBody Customer customer) {
		 if(customer.getId() == null) {
			 generateNewAccountToken(customer);
		 } else {
			 generateServiceToken(customer);
		 }
	 }*/
	 
	 /*public Token generateNewAccountToken(Customer customer) {
		 Customer newCustomer = customerRepository.save(customer);
		 Token token = tokenGenerator.generate(customer);
		 assignCounter();
		 return token;
	 }*/
}

package com.example.assignment.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.enums.ServiceType;
import com.example.assignment.model.Counter;
import com.example.assignment.model.Customer;
import com.example.assignment.model.Token;

@RestController
@RequestMapping(value="/rest/api/banking")
public class Controller {
	@Autowired
	BankingService service;
		
	/**
	  * GET /counters  --> Create a new department and save it in the database.
	  */
	 @RequestMapping(value="/counters", method=RequestMethod.GET)
	 public Map<String, Counter> getCounters() {
		 return service.getCounters();
	 }
	 
	 /**
	  * POST /create  --> Create a new department and save it in the database.
	  */
	 @RequestMapping(value="/counters", method=RequestMethod.POST)
	 public Map<String, Counter> addCounters() {
		 return service.addCounters();
	 }
	 
	 @RequestMapping(value="/counters/type", method=RequestMethod.GET)
	 public List<Counter> getServiceCounters(@RequestParam ServiceType type) {
		 return service.getServiceCounters(type);
	 }

	 @RequestMapping(value="/token", method=RequestMethod.POST)
	 public void processService(@RequestBody Customer customer) throws Exception {		
		 service.process(customer);
	 }
	 
	 @RequestMapping(value="/token", method=RequestMethod.POST)
	 public void processService(@RequestBody Token token) throws Exception {		
		 service.operate(token);
	 }
}

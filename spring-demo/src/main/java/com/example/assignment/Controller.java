package com.example.assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/api/banking")
public class Controller {
	@Autowired
	BankingRepository bankRepository;
		
	/**
	  * GET /create  --> Create a new department and save it in the database.
	  */
	 @RequestMapping(value="/counters")
	 public Map<String, Counter> getCounters() {
		 Map<String, Counter> dataMap = new HashMap<String, Counter>();
		 for (Counter countr : BankingApplication.counters) {
			 dataMap.put(countr.toString(), countr);
		 }
		 return dataMap;
	 }
}

package com.example.assignment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
	private static TokenGenerator instance = null;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TokenGenerator.class);
	@Autowired
	CounterRepository counterRepository;
	private TokenGenerator(){
		
	}
	// static method to create instance of Singleton class
    public static TokenGenerator getInstance()
    {
        // To ensure only one instance is created
        if (instance == null)
        {
        	instance = new TokenGenerator();
        }
        return instance;
    }
    public Token generate(Customer customer){
    	Token token = new Token(customer.getAccountType().getCode());
    	//Always taking service at index:0
    	List<Counter> serviceCounters = counterRepository.findByServiceType(customer.getActiveService());
    	if(serviceCounters.isEmpty()) {
    		LOGGER.info("No counter available for this service for now. Please try again.");
    		System.out.println("No counter available for this service for now. Please try again.");
    	}
    	int minIndex = serviceCounters.get(0).getRank();
    	for (int i = 1; i < serviceCounters.size(); i++) {
    		minIndex = minIndex < serviceCounters.get(i).getRank() ? 
    				minIndex:serviceCounters.get(i).getRank();
		}	
    	return token;
    } 
    
    public static void main(String[] args) {
		List<Token> tokens = new ArrayList<>();
		tokens.add(new Token(1,1));
		tokens.add(new Token(2, 0));
		tokens.add(new Token(3, 0));
		int minIndex = tokens.size();
		for (Token token : tokens) {
			if (token.getPriority() == AccountType.REGULAR.getCode()) {
				minIndex = tokens.indexOf(token);
				break;
			}
		}
		System.out.println(minIndex);
		LOGGER.info("No counter available for this service for now. Please try again.");
	}
    
}

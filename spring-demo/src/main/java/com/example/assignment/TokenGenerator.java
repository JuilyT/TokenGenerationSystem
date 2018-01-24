package com.example.assignment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import com.example.assignment.config.CounterOperator;
import com.example.assignment.enums.AccountType;
import com.example.assignment.enums.ServiceType;
import com.example.assignment.enums.Status;
import com.example.assignment.model.Counter;
import com.example.assignment.model.Customer;
import com.example.assignment.model.Token;
import com.example.assignment.repository.CounterRepository;

@Component
public class TokenGenerator {
	private static TokenGenerator instance = null;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TokenGenerator.class);
	@Autowired
	CounterRepository counterRepository;
	@Autowired
	CounterOperator operator;
	@Autowired
	Initializer initializer;
	
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
    
    public void generate(Customer customer) throws Exception{  
    	if (customer.getActiveService()==null) {
			 LOGGER.info("No service to serve for this customer");
			 return;
		} 
    	Token token = new Token(customer, customer.getAccountType().getCode());
    	token.setId(Integer.parseInt(initializer.generateRandom(5)));
    	token.setCounter(assignTokenCounter(token, customer));
    	token.setStatus(Status.IN_PROGRESS);
    	token.setLastUpdated(new Timestamp(System.currentTimeMillis()));
    	LOGGER.info("Token :{} generated for customer: {}", new Object[]{token.toString(),customer.toString()});
    } 
    
    private Counter assignTokenCounter(Token token, Customer customer) throws Exception {
    	//Always taking service at index:0  
    	List<Counter> serviceCounters = counterRepository.findByServiceType(customer.getActiveService());
    	if(serviceCounters.isEmpty()) {
    		LOGGER.info("No counter available for this service for now. Please try again.");
    		throw new Exception("No counter available for this service for now. Please try again.");
    	}
    	Counter availableCounter = serviceCounters.get(0);
    	int minIndex = operator.getRank(availableCounter);
    	for (int i = 1; i < serviceCounters.size(); i++) {
    		Counter currentCounter = serviceCounters.get(i);
    		if (minIndex > operator.getRank(currentCounter)) {
				minIndex = operator.getRank(currentCounter);
				availableCounter = currentCounter;
			}
		}
    	if (minIndex==0 && availableCounter.getTokens()==null) {
			List<Token> counterTokens = new ArrayList<>();
			counterTokens.add(minIndex, token);
			availableCounter.setTokens(counterTokens);
		} else {
			availableCounter.getTokens().add(minIndex, token);
		}
    	LOGGER.info("Assigning token :{} to counter: {}", 
    			new Object[]{token.toString(), availableCounter.toString()});
    	return availableCounter;
    }
    
    public static void main(String[] args) throws Exception {
		List<ServiceType> services = new ArrayList<>();
		services.add(ServiceType.DEPOSIT);
		services.add(ServiceType.WITHDRAWL);
		Customer cust = new Customer("12345678910", "juilyk", AccountType.PREMIUM, services);
		//processToken(cust); 
	}
    
}

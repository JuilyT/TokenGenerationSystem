package com.example.assignment.config;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.example.assignment.TokenGenerator;
import com.example.assignment.enums.AccountType;
import com.example.assignment.enums.Status;
import com.example.assignment.model.Counter;
import com.example.assignment.model.Customer;
import com.example.assignment.model.Token;

public class OperatorImplementor implements CounterOperator {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TokenGenerator.class);
	@Autowired
	TokenGenerator tokenGenerator;
	
	@Override
	public int getRank(Counter counter) {
		List<Token> tokens = counter.getTokens();
		if (CollectionUtils.isEmpty(tokens)) {
			return 0;
		}
		int minIndex = tokens.size();
		for (Token token : tokens) {
			if (token.getPriority() == AccountType.REGULAR.getCode()) {
				return tokens.indexOf(token);
			}
		}
		return minIndex;
	}

	@Override
	public void operate(Counter counter) throws Exception {
		Token activeToken = counter.getActiveToken();
		Customer customer = activeToken.getCustomer();
		// Operating on customer service and removing it from queue
		customer.getServices().remove(0);
		activeToken.setStatus(Status.COMPLETED);
		counter.getTokens().remove(activeToken);
		if (customer.getActiveService()==null) {
			 LOGGER.info("No service to serve for this customer");
			 return;
		} else {
			tokenGenerator.generate(customer);
		}
	}

}

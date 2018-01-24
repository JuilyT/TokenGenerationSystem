package com.example.assignment.config;

import com.example.assignment.model.Counter;

public interface CounterOperator {
	public int getRank(Counter counter);
	public void operate(Counter counter) throws Exception;
}

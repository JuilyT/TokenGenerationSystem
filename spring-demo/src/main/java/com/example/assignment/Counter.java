package com.example.assignment;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Counter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Enumerated(EnumType.STRING)
	@Column
	private ServiceType serviceType;
	@Transient
	private List<Token> tokens;
	public Counter(int id, ServiceType type) {
		this.id=id;
		this.serviceType=type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	/*public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}*/
	
	@Override
	public String toString() {
		return "Counter [id=" + id + ", serviceType=" + serviceType + "]";
	}
	
	/*public List<Token> getTokens() {
		List<Token> tokens = new ArrayList<>();
		for (Customer customer : safe(customers)) {
			tokens.add(customer.getToken());
		}
		return tokens;
	}*/
	
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	//It can be put in Utility class and make generic method
	public static List<Customer> safe( List<Customer> other ) {
	    return other == null ? Collections.emptyList() : other;
	}
}

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
import javax.validation.constraints.NotNull;

@Entity
public class Counter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private ServiceType serviceType;
	@Transient
	private List<Token> tokens;
	public Counter(){super();};
	public Counter(int id, ServiceType type) {
		this.id=id;
		this.serviceType=type;
	}
	public Counter(ServiceType type) {
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
	
	@Override
	public String toString() {
		return "Counter [id=" + id + ", serviceType=" + serviceType + "]";
	}
	
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	//It can be put in Utility class and make generic method
	public static List<Token> safe( List<Token> other ) {
	    return other == null ? Collections.emptyList() : other;
	}
	
	public int getRank() {
		int minIndex = tokens.size();
		for (Token token : safe(tokens)) {
			if (token.getPriority() == AccountType.REGULAR.getCode()) {
				return tokens.indexOf(token);
			}
		}
		return minIndex;
	}
}

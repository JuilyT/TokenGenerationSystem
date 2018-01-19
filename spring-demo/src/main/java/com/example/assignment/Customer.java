package com.example.assignment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum AccountType{premium,regular};
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String accountNo;
	@Column
	private String name;
	@Column
	private String address;
	@Enumerated(EnumType.STRING)
	@Column(length=15)
	private AccountType accountType;
	@Transient
	private List<ServiceType> services;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
	private Token token;
	
	public Customer(String accountNo, String name, AccountType accountType, List<ServiceType> services) {
		this.accountNo = accountNo;
		this.name = name;
		this.accountType = accountType;
		this.services = services;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public List<ServiceType> getServices() {
		return services;
	}
	public void setServiceType(List<ServiceType> services) {
		this.services = services;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "Customer [accountNo=" + accountNo + ", name=" + name + ", "
				+ "accountType=" + accountType + ", services="+ services + "]";
	}
	
	public ServiceType getActiveService() {
		return (!services.isEmpty()) ? services.get(0):null;
	}
}

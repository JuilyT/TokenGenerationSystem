package com.example.assignment.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.example.assignment.enums.AccountType;
import com.example.assignment.enums.ServiceType;

@Entity
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String accountNo;
	@Column
	private String name;
	@Column
	private String address;
	@NotNull
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

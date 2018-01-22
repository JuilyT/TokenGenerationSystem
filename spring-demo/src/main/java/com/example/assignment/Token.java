package com.example.assignment;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Enumerated(EnumType.STRING)
    @Column(length=15)
	private Status status;
	// 1- premium, 0- regular
	@Column
	private int priority;
	@Column
	private Timestamp lastUpdated;
	public Token(int priority) {
		this.priority = priority;
	}
	public Token(int id, int priority) {
		this.id=id;
		this.priority = priority;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}

package com.example.assignment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, Integer>{
	public List<Counter> findByServiceType(ServiceType serviceType);
}

package com.mrc.roadways.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrc.roadways.entity.Salesperson;
import com.mrc.roadways.repository.SalespersonRepo;

@Service
public class SalesPersonService {
	
	@Autowired
	SalespersonRepo repo;
	
	public Salesperson getSalesPersonDetails(int salesId) {
		return repo.findBySalesId(salesId);
	}
	
}

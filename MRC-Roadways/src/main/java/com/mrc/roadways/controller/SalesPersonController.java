package com.mrc.roadways.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrc.roadways.entity.Salesperson;
import com.mrc.roadways.service.SalesPersonService;

@RestController
@RequestMapping(value="/salesperson")
public class SalesPersonController {
	
	@Autowired
	SalesPersonService salesService;
	
	
	@RequestMapping(value="/{salesId}", method=RequestMethod.GET)
	public Salesperson getName(@PathVariable int salesId) {
		Salesperson salesp = salesService.getSalesPersonDetails(salesId);
		return salesp;
	}

}

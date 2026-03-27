package com.mrc.roadways.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/salesperson")
public class SalesPersonController {
	
	@RequestMapping(value="/details", method=RequestMethod.GET)
	public String getName() {
		return "Sam";
	}

}

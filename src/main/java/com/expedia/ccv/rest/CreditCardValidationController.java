package com.expedia.ccv.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreditCardValidationController {
	
	@RequestMapping(value = "/ashish", method = RequestMethod.GET)
	public String getstring() {
		return "Ashish Patel";
	}
}

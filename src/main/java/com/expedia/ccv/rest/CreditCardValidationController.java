package com.expedia.ccv.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;
import com.expedia.ccv.service.CreditCardValidationService;

@RestController
@RequestMapping("/api")
public class CreditCardValidationController {
	
	@Autowired
	CreditCardValidationService creditCardValidationService;

	@PostMapping("/creditCard")
	public ResponseEntity<?> getstring(@RequestBody CreditCardInfoDto dto, UriComponentsBuilder ucBuilder) {
		
		System.out.println("DTO ========> "+dto);
		ResponseMessageDto rmd = new ResponseMessageDto();
		//HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/creditCard").buildAndExpand().toUri());
		String result = creditCardValidationService.validateCard(dto);
		System.out.println(result);
        return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
}

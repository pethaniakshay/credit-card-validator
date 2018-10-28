package com.expedia.ccv.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.expedia.ccv.constants.HttpStatusCodes;
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
		//ResponseMessageDto rmd = new ResponseMessageDto();
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/creditCard").buildAndExpand().toUri());
        ResponseMessageDto rmd = creditCardValidationService.validateCard(dto);
        rmd.setHttpHeaders(headers);
		if(rmd.isError()) {
			rmd.setResponseCode(HttpStatusCodes.UNPROCESSABLE_ENTITY.getCode());
			rmd.setResponseMessage("validation failed");
		}else {
			rmd.setResponseCode(HttpStatusCodes.SUCCESS.getCode());
			rmd.setResponseMessage("Credit card is valid");
		}
        return new ResponseEntity<ResponseMessageDto>(rmd, HttpStatus.CREATED);
	}
}

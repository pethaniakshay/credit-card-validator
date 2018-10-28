package com.expedia.ccv.rest;

import com.expedia.ccv.service.CreditCardValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;

@RestController
public class CreditCardValidationController {

  @Autowired
  private CreditCardValidationService creditCardValidationService;

	@PostMapping("/creditCard")
	public ResponseEntity<?> getstring(@RequestBody CreditCardInfoDto dto, UriComponentsBuilder ucBuilder) {

		//ResponseMessageDto rmd = new ResponseMessageDto();
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/creditCard").buildAndExpand().toUri());
        ResponseMessageDto rmd = creditCardValidationService.validateCard(dto);
        rmd.setHttpHeaders(headers);
		if(rmd.isError()) {
			rmd.setResponseCode(HttpStatus.UNPROCESSABLE_ENTITY.toString());
			rmd.setResponseMessage("validation failed");
			return new ResponseEntity<ResponseMessageDto>(rmd, HttpStatus.UNPROCESSABLE_ENTITY);
		}else {
			rmd.setResponseCode(HttpStatus.OK.toString());
			rmd.setResponseMessage("Credit card is valid");
		}
        return new ResponseEntity<ResponseMessageDto>(rmd, HttpStatus.OK);
	}
}

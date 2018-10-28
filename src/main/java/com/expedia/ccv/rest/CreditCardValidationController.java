package com.expedia.ccv.rest;

import com.expedia.ccv.dto.CreditCardDto;
import com.expedia.ccv.service.CreditCardValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-card/validate")
public class CreditCardValidationController {

  @Autowired
  private CreditCardValidationService creditCardValidationService;

  @PostMapping
  public ResponseEntity<Object> validateCreditCard(@RequestBody CreditCardDto cardDto){

    creditCardValidationService.validateCard(cardDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}

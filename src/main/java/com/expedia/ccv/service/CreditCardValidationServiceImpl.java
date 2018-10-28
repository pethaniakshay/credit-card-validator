package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class CreditCardValidationServiceImpl implements CreditCardValidationService{

  private static final Logger log = LoggerFactory.getLogger(CreditCardValidationServiceImpl.class);

  @Autowired
  private Set<Long> blockedCards;

  @Override
  public void validateCard(CreditCardDto cardDto) {

    log.debug("Card No: {}  ||  Expiry Date: {}", cardDto.getCardNo(), cardDto.getExpiryDate());

    //TODO Format Number and Expiry date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    YearMonth expiryDate = YearMonth.parse(cardDto.getExpiryDate(),formatter);
    log.debug("Time : "+ expiryDate.toString());

    //TODO Check weather card is blocked
    log.debug("BLocked Card Size: {}", blockedCards.size());

    //TODO Validate expiry date
    if(expiryDate.isBefore(YearMonth.now())){
      log.debug("Invalid");
      //TODO throw exception
    }

    //TODO Validate Number
  }
}

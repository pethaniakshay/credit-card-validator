package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardDto;

public interface CreditCardValidationService {
  public void validateCard(CreditCardDto cardDto);
}

package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardDto;
import com.expedia.ccv.dto.CreditCardInfoDto;

public interface CreditCardValidationService {
  public void validateCard(CreditCardDto cardDto);

	public String validateCard(CreditCardInfoDto creditCardInfoDto);
}

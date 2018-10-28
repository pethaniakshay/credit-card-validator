package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardDto;
import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;

public interface CreditCardValidationService {

  public void validateCard(CreditCardDto cardDto);

	public ResponseMessageDto validateCard(CreditCardInfoDto creditCardInfoDto);

}

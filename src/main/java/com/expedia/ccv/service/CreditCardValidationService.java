package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardInfoDto;

public interface CreditCardValidationService {
	
	public String validateCard(CreditCardInfoDto creditCardInfoDto);
	
}

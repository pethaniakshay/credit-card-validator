package com.expedia.ccv.dao;

import com.expedia.ccv.dto.CreditCardInfoDto;

public interface CreditCardValidationDao {

	public String validateCard(CreditCardInfoDto creditCardInfoDto);
}

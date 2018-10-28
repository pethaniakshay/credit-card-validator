package com.expedia.ccv.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CreditCardInfoDto implements Serializable {
	
	private static final long serialVersionUID = 112940570436386809L;
	
	private String cardNumber;
	private String expiryDate;

}

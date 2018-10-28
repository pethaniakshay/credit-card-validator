package com.expedia.ccv.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
public class ResponseMessageDto {

	private String responseCode;

	private boolean isError;

	private String responseMessage;

	private String errorDiscription;

	private Object data;

}

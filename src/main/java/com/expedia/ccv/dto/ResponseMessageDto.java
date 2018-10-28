package com.expedia.ccv.dto;

import org.springframework.http.HttpHeaders;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class ResponseMessageDto {

	private String responseCode;

	private boolean isError;

	private String responseMessage;

	private String errorDiscription;
	
	private HttpHeaders httpHeaders;

}

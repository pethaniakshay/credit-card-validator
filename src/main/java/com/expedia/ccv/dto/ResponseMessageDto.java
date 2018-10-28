package com.expedia.ccv.dto;

import lombok.*;
import org.springframework.http.HttpHeaders;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessageDto {

	private String responseCode;

	private boolean isError;

	private String responseMessage;

	private String errorDiscription;
	
	private HttpHeaders httpHeaders;

}

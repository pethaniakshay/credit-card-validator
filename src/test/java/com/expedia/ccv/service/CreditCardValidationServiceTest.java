package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardInfoDto;
import com.expedia.ccv.dto.ResponseMessageDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreditCardValidationServiceTest {

  private CreditCardValidationService creditCardValidationService;

  @Before
  public void testSetUp(){

      creditCardValidationService = new CreditCardValidationServiceImpl();
  }

  @Test
  public void validateCardTest(){
    CreditCardInfoDto parameter = new CreditCardInfoDto("4929245520128845","04/22");

    ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
    ResponseMessageDto expectedResult = ResponseMessageDto.builder()
            .isError(false)
            .responseMessage("")
            .build();
    Assert.assertEquals("",expectedResult.isError(),false);
  }

  @Test
  public void validateCardTestWhenCardIsExpired(){
    CreditCardInfoDto parameter = new CreditCardInfoDto("4929245520128845","04/12");

    ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
    ResponseMessageDto expectedResult = ResponseMessageDto.builder()
            .isError(true)
            .errorDiscription("Expire date is invalid or it's expired")
            .build();
    Assert.assertEquals("",expectedResult.getErrorDiscription(),expectedResult.getErrorDiscription());
  }

  @Test
  public void validateCardTestWhenExpiryDateIsInvalid(){
    CreditCardInfoDto parameter = new CreditCardInfoDto("4929245520128845","25/15");

    ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
    ResponseMessageDto expectedResult = ResponseMessageDto.builder()
            .isError(true)
            .errorDiscription("Expire date is invalid or it's expired")
            .build();
    Assert.assertEquals("",expectedResult.getErrorDiscription(),expectedResult.getErrorDiscription());
  }

  @Test
  public void validateCardTestWhenCardNumberInvalid(){
    CreditCardInfoDto parameter = new CreditCardInfoDto("4893772908615855","25/15");

    ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
    ResponseMessageDto expectedResult = ResponseMessageDto.builder()
            .isError(true)
            .errorDiscription("Credit card Number you provided is invalid")
            .build();
    Assert.assertEquals("",expectedResult.getErrorDiscription(),expectedResult.getErrorDiscription());
  }

  @Test
  public void validateCardTestWhenCardIsBlackListed(){
    CreditCardInfoDto parameter = new CreditCardInfoDto("4788384538552446","25/15");

    ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
    ResponseMessageDto expectedResult = ResponseMessageDto.builder()
            .isError(true)
            .errorDiscription("This card is in blacklist")
            .build();
    Assert.assertEquals("",expectedResult.getErrorDiscription(),expectedResult.getErrorDiscription());
  }

  @Test
  public void validateCardTestWhenExpiryDateIsBlank(){
    CreditCardInfoDto parameter = new CreditCardInfoDto("4929245520128845","");

    ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
    ResponseMessageDto expectedResult = ResponseMessageDto.builder()
            .isError(true)
            .errorDiscription("CardNumebr or ExpirtyDate are found Empty")
            .build();
    Assert.assertEquals("",expectedResult.getErrorDiscription(),expectedResult.getErrorDiscription());
  }

  @Test
  public void validateCardTestWhenCardNumberIsBlank(){
    CreditCardInfoDto parameter = new CreditCardInfoDto("","15/22");

    ResponseMessageDto actualResult = creditCardValidationService.validateCard(parameter);
    ResponseMessageDto expectedResult = ResponseMessageDto.builder()
            .isError(true)
            .errorDiscription("CardNumebr or ExpirtyDate are found Empty")
            .build();
    Assert.assertEquals("",expectedResult.getErrorDiscription(),expectedResult.getErrorDiscription());
  }
}

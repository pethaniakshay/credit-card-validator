package com.expedia.ccv.service;

import com.expedia.ccv.dto.CreditCardDto;
import com.expedia.ccv.dto.CreditCardInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class CreditCardValidationServiceImpl implements CreditCardValidationService{

  private static final Logger log = LoggerFactory.getLogger(CreditCardValidationServiceImpl.class);

  @Autowired
  private Set<Long> blockedCards;

  @Override
  public void validateCard(CreditCardDto cardDto) {

    log.debug("Card No: {}  ||  Expiry Date: {}", cardDto.getCardNo(), cardDto.getExpiryDate());

    //TODO Format Number and Expiry date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
    YearMonth expiryDate = YearMonth.parse(cardDto.getExpiryDate(),formatter);
    log.debug("Time : "+ expiryDate.toString());

    //TODO Check weather card is blocked
    log.debug("BLocked Card Size: {}", blockedCards.size());

    //TODO Validate expiry date
    if(expiryDate.isBefore(YearMonth.now())){
      log.debug("Invalid");
      //TODO throw exception
    }

    //TODO Validate Number
  }


	private static List<String> blackListNumber;

	static {
		blackListNumber = addBlackListNumber();
	}

	@Override
	public String validateCard(CreditCardInfoDto cardInfo) {
		String result = "";
		String cardNumber = "";
		try {
			if (null != cardInfo.getCardNumber() && !cardInfo.getCardNumber().isEmpty()
					&& null != cardInfo.getExpiryDate() && !cardInfo.getExpiryDate().isEmpty()) {

				cardNumber = cardInfo.getCardNumber().replaceAll("\\s", "");
				if (!blackListNumber.contains(cardNumber)) {

					if (Pattern.matches("^4[0-9]{15}$", cardNumber)
							|| Pattern.matches("^5[1-5][0-9]{14}$", cardNumber)) {
						if (!validateCardExpiryDate(cardInfo.getExpiryDate())) {
							if (validateCreditCardByLuhn(Long.parseLong(cardNumber))) {
								result = "Credit card is valid";
							} else {
								result = "Credit card Number you provided is invalid";
							}
						} else {
							result = "Expire date is invalid or it's expired";
						}
					} else {
						result = "Only Visa and MasterCard are accepted Please Enter valid card";
					}

				} else {
					result = "This card is in blacklist";
				}

			} else {
				result = "CardNumebr or ExpirtyDate are found Empty";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private boolean validateCreditCardByLuhn(long number) {
		boolean isValid = false;
		if ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0) {
			isValid = true;
		}
		return isValid;
	}

	private boolean validateCardExpiryDate(String date) {
		boolean isExpired = false;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/YY");
			simpleDateFormat.setLenient(false);
			Date expiry = simpleDateFormat.parse(date);
			isExpired = expiry.before(new Date());
		} catch (ParseException e) {
			isExpired = true;
		}
		return isExpired;

	}

	private static List<String> addBlackListNumber() {
		List<String> blackListNumber = new ArrayList<>();
		blackListNumber.add("4788384538552446");
		blackListNumber.add("5144385438523845");
		return blackListNumber;
	}

	// Get the result from Step 2
	private static int sumOfDoubleEvenPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 2; i >= 0; i -= 2)
			sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

		return sum;
	}

	// Return sum of odd-place digits in number
	private static int sumOfOddPlace(long number) {
		int sum = 0;
		String num = number + "";
		for (int i = getSize(number) - 1; i >= 0; i -= 2)
			sum += Integer.parseInt(num.charAt(i) + "");
		return sum;
	}

	// Return the number of digits in d
	private static int getSize(long d) {
		String num = d + "";
		return num.length();
	}

	// Return this number if it is a single digit, otherwise,
	// return the sum of the two digits
	private static int getDigit(int number) {
		if (number < 9)
			return number;
		return number / 10 + number % 10;
	}
}

# Credit Card Validator

Credit card validator REST API in spring boot

URL : localhost:8080/CreditCardValidatorApp/creditCard/

Test cases 1 : 

Input :
{
    "cardNumber": "5127657160767608",
    "expiryDate": "01/20"
}

Output : 
{
    "responseCode": 200,
    "responseMessage": "Your Credit Card is Valid",
    "errorDiscription": "",
    "httpHeaders": {
        "Location": [
            "http://localhost:8080/CreditCardValidatorApp/creditCard"
        ]
    },
    "error": false
}


Test cases 2 :

Input :
{
    "cardNumber": "3227657160767608",
    "expiryDate": "01/20"
}

Output : 
{
    "responseCode": 422,
    "responseMessage": "Credit Card Validation Fail",
    "errorDiscription": "Only Visa/Mastercard are Accepted",
    "httpHeaders": {
        "Location": [
            "http://localhost:8080/CreditCardValidatorApp/creditCard"
        ]
    },
    "error": true
}

Test cases 3 :

Input :
{
    "cardNumber": "5127657160767608",
    "expiryDate": "010/200"
}

Output : 
{
    "responseCode": 422,
    "responseMessage": "Credit Card Validation Fail",
    "errorDiscription": "Invalid Expiration Date or Date is Expired",
    "httpHeaders": {
        "Location": [
            "http://localhost:8080/CreditCardValidatorApp/creditCard"
        ]
    },
    "error": true
}

Test cases 4 :

Input :
{
    "cardNumber": "4788384538552446",
    "expiryDate": "10/20"
}

Output : 
{
    "responseCode": 422,
    "responseMessage": "Credit Card Validation Fail",
    "errorDiscription": "The Credit Card Number You Provided is in Blacklist",
    "httpHeaders": {
        "Location": [
            "http://localhost:8080/CreditCardValidatorApp/creditCard"
        ]
    },
    "error": true
}

Test cases 4 :

Input :
{
    "cardNumber": "",
    "expiryDate": ""
}

Output : 
{
    "responseCode": 422,
    "responseMessage": "Credit Card Validation Fail",
    "errorDiscription": "Credit Card Number or Date found Empty",
    "httpHeaders": {
        "Location": [
            "http://localhost:8080/CreditCardValidatorApp/creditCard"
        ]
    },
    "error": true
}
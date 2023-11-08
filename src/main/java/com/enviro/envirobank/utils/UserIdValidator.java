package com.enviro.envirobank.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserIdValidator implements ConstraintValidator<ValidateId, String> {
    @Override
    public boolean isValid(String identityNumber, ConstraintValidatorContext context) {
            if(identityNumber.length() != 13){
                return false;
            }else{
                return  validate(identityNumber);
            }
    }
    public static Boolean validate(String id){
        int sumOdd = 0, sumEven = 0, _doubled;
        int summation;
        try {
            int length = id.length() - 1;
            char[] chars = id.toCharArray();
            String check = id.substring(12);
            for(int x  = 0; x < length; x++){
                if(x % 2 == 0 ){
                    String numString = String.valueOf(chars[x]);
                    int numbers = Integer.parseInt(numString);
                    sumOdd += numbers;
                }else if(x % 2 != 0){
                    String numString = String.valueOf(chars[x]);
                    int numbers = Integer.parseInt(numString);
                    int doubled = numbers * 2;
                    if(doubled > 9){
                        _doubled = doubled - 9;
                    }else{
                        _doubled = doubled;
                    }
                    sumEven += _doubled;
                }
            }
            summation = sumOdd + sumEven;
            int checksum = Integer.parseInt(check);
            if((summation * 9) % 10 == checksum)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Boolean.FALSE;
        }
    }
}

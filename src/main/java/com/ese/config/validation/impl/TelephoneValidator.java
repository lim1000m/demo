package com.ese.config.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ese.config.validation.Telephone;

import java.util.regex.Matcher; 

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {
    private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^0\\d{1,3}-[0-9]\\d{2,3}-\\d{4}$");

    public void initialize(Telephone annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
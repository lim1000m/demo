package com.ese.config.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ese.config.validation.Kor;

import java.util.regex.Matcher; 


public class KoreanValidator implements ConstraintValidator<Kor, String> {
	private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[\uAC00-\uD7A3]+$");
    
    public void initialize(Kor annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
package com.ese.config.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ese.config.validation.EngNum;

import java.util.regex.Matcher; 

public class EngNumValidator implements ConstraintValidator<EngNum, String> {
	private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[a-zA-Z0-9]+$");
    
    public void initialize(EngNum annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
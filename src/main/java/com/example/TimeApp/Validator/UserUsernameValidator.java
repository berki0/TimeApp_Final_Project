package com.example.TimeApp.Validator;

import com.example.TimeApp.Constraint.UserConstraint;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UserUsernameValidator implements ConstraintValidator<UserConstraint, String> {


    @Override
    public void initialize(UserConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length()>3&&s.length()<=10;//userService.uniqueUserName(s);
    }
}
package com.example.TimeApp.Constraint;

import com.example.TimeApp.Validator.UserUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserUsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserConstraint {

    String message() default "Username is occupied ";
    Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}


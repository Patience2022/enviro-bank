package com.enviro.envirobank.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserIdValidator.class)

public @interface ValidateId {

    public String message() default "Enter a valid South African ID as per your ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

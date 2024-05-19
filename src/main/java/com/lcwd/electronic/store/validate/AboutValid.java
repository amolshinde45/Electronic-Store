package com.lcwd.electronic.store.validate;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AboutValidator.class)
public @interface AboutValid {


    String message() default "Write something about User !!";

    //represent group of constrains
    Class<?>[] groups() default {};


    //additional info about annotation
    Class<? extends Payload>[] payload() default {};

}

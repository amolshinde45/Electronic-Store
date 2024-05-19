package com.lcwd.electronic.store.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AboutValidator implements ConstraintValidator<AboutValid, String> {

    Logger logger= LoggerFactory.getLogger(AboutValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        logger.info("About message validator contains message : ",s);
        if (s.isBlank()){
            return false;
        }
        else return true;



    }
}

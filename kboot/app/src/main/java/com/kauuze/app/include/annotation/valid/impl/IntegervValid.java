package com.kauuze.app.include.annotation.valid.impl;

import com.kauuze.app.include.annotation.valid.Integerv;
import com.kauuze.app.include.RU;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class IntegervValid implements ConstraintValidator<Integerv,Object> {
    private boolean require = true;

    @Override
    public void initialize(Integerv constraintAnnotation) {
        if(!constraintAnnotation.require()){
            require = false;
        }
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if(RU.validRequire(o,require)){
                return true;
            }
            Integer num = Integer.valueOf(String.valueOf(o));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

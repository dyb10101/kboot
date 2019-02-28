package com.kauuze.app.include.annotation.valid.impl;

import com.kauuze.app.include.annotation.valid.Longv;
import com.kauuze.app.include.RU;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class LongvValid implements ConstraintValidator<Longv,Object> {
    private boolean require = true;

    @Override
    public void initialize(Longv constraintAnnotation) {
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
            long num = Long.valueOf(String.valueOf(o));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

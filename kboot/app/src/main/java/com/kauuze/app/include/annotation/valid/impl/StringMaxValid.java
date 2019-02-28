package com.kauuze.app.include.annotation.valid.impl;

import com.kauuze.app.include.annotation.valid.StringMax;
import com.kauuze.app.include.RU;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class StringMaxValid implements ConstraintValidator<StringMax,Object> {
    private boolean require = true;
    private int max;
    private int min;

    @Override
    public void initialize(StringMax constraintAnnotation) {
        if(!constraintAnnotation.require()){
            require = false;
        }
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if(RU.validRequire(o,require)){
                return true;
            }
           int len = String.valueOf(o).length();
            if(len > max || len < min){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

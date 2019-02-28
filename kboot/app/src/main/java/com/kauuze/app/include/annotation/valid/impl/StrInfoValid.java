package com.kauuze.app.include.annotation.valid.impl;

import com.kauuze.app.include.annotation.valid.StrInfo;
import com.kauuze.app.include.RU;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class StrInfoValid implements ConstraintValidator<StrInfo,Object> {
    private boolean require = true;
    private int max;

    @Override
    public void initialize(StrInfo constraintAnnotation) {
        if(!constraintAnnotation.require()){
            require = false;
        }
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if(RU.validRequire(o,require)){
                return true;
            }
            if(RU.strInfoMax(String.valueOf(o),max)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}

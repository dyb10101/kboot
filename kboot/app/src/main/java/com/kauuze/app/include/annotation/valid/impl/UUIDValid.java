package com.kauuze.app.include.annotation.valid.impl;

import com.kauuze.app.include.annotation.valid.UUID;
import com.kauuze.app.include.RU;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class UUIDValid implements ConstraintValidator<UUID,Object> {
    private boolean require = true;

    @Override
    public void initialize(UUID constraintAnnotation) {
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
            if(RU.isUUid(String.valueOf(o))){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}

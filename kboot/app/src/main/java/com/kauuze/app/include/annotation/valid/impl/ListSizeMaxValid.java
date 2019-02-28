package com.kauuze.app.include.annotation.valid.impl;

import com.kauuze.app.include.annotation.valid.ListSizeMax;
import com.kauuze.app.include.BoxUtil;
import com.kauuze.app.include.RU;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class ListSizeMaxValid implements ConstraintValidator<ListSizeMax,Object> {
    private boolean require = true;
    private int max;

    @Override
    public void initialize(ListSizeMax constraintAnnotation) {
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
            if(RU.listSizeMax(BoxUtil.copy(o,List.class),max)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}

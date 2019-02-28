package com.kauuze.app.include;

import java.math.BigDecimal;

/**
 * BigDecimal运算工具类
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class BdcUtil {

    /**
     * 加法
     * @param value
     * @param value2
     * @return
     */
    public static BigDecimal bigDecimalAdd(BigDecimal value,BigDecimal value2){
        return value.add(value2);
    }

    /**
     * 减法
     * @param value
     * @param value2
     * @return
     */
    public static BigDecimal bigDecimalSub(BigDecimal value,BigDecimal value2){
        return value.subtract(value2);
    }

    /**
     * 乘法
     * @param value
     * @param value2
     * @return
     */
    public static BigDecimal bigDecimalMultiply(BigDecimal value,BigDecimal value2){
        return value.multiply(value2);
    }

    /**
     * 除法,保留2位小数四舍五入
     * @param value
     * @param value2
     * @return
     */
    public static BigDecimal bigDecimalDivide(BigDecimal value, BigDecimal value2){
        return value.divide(value2, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 保留2位小数(四舍五入)
     * @param value
     * @return
     */
    public static BigDecimal bigDecimalSaved(BigDecimal value){
        return value.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 两个BigDecimal,是否前一个比后一个大
     * @return
     */
    public static boolean isBigDecimalGt(BigDecimal value,BigDecimal value2){
        if(value == null || value2 == null){
            return false;
        }
        int result = value.compareTo(value2);
        if(result == 1){
            return true;
        }
        return false;
    }

    /**
     * 两个BigDecimal,是否相等
     * @param value
     * @param value2
     * @return
     */
    public static boolean isBigDecimalEq(BigDecimal value, BigDecimal value2){
        if(value == null || value2 == null){
            return false;
        }
        int result = value.compareTo(value2);
        if(result == 0){
            return true;
        }
        return false;
    }

    /**
     * 两个BigDecimal,是否前一个比后一个大或相等
     * @param value
     * @param value2
     * @return
     */
    public static boolean isBigDecimalGtOrEq(BigDecimal value, BigDecimal value2){
        if(value == null || value2 == null){
            return false;
        }
        int result = value.compareTo(value2);
        if(result == 1 || result == 0){
            return true;
        }
        return false;
    }
}

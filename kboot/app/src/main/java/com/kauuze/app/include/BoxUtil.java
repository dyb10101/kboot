package com.kauuze.app.include;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常用方法工具类
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class BoxUtil {

    /**
     * 将以下划线为截断的字符串变为字符串数组
     * @param s
     * @param length
     * @return
     */
    public static String[] splitUnderline(String s,int length){
        String[] some = s.split("_");
        if(some.length != length){
            throw new RuntimeException("拆分下划线值length不匹配");
        }
        return some;
    }

    /**
     * 将毫秒值转换为可视的格式
     * @param mill
     * @return
     */
    public static String millToView(Long mill){
        String fomart = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(fomart);
        return df.format(mill);
    }

    /**
     * 将8位日期截断放在map里
     * @param dateStr
     * @return
     */
    public static Map<String,String> dateStrToMap(String dateStr){
        String year = dateStr.substring(0,4);
        String month = dateStr.substring(4,6);
        String day = dateStr.substring(6,8);
        Map<String,String> map = new HashMap<>();
        map.put("year",year);
        map.put("month",month);
        map.put("day",day);
        return map;
    }

    /**
     * 将13位毫秒值截断放入map里
     * @param mill
     * @return
     */
    public static Map<String,String> millToMap(Long mill){
        if(!RU.isMill(mill)){
            throw new RuntimeException("millToMap error");
        }
        String fomart = "yyyy_MM_dd_HH_mm_ss_SS";
        SimpleDateFormat df = new SimpleDateFormat(fomart);
        String str = df.format(mill);
        String[] strs = splitUnderline(str,7);
        Map<String,String> map = new HashMap();
        map.put("year",strs[0]);
        map.put("month",strs[1]);
        map.put("day",strs[2]);
        map.put("hour",strs[3]);
        map.put("min",strs[4]);
        map.put("second",strs[5]);
        map.put("millisecond",strs[6]);
        return map;
    }

    /**
     * 将对象转为json格式
     * @param source
     * @return
     */
    public static String toJsonString(Object source){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(source);
            return jsonString;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("toJsonString转换失败");
        }
    }

    /**
     *深拷贝,自动缺省字段
     * @param jsonString
     * @param targetType
     * @param <T>
     * @return
     */
    public static <T> T parseJsonString(String jsonString,Class<T> targetType){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            T target = objectMapper.readValue(jsonString,targetType);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("parseJsonString转换失败");
        }
    }

    /**
     * 类型转换:深拷贝,自动缺省字段
     * @param o
     * @param targetType
     * @param <T>
     * @return
     */
    public static <T> T copy(Object o,Class<T> targetType){
        return parseJsonString(toJsonString(o),targetType);
    }

    /**
     * 将字符串放入list中
     * @param str
     * @return
     */
    public static List<String> putStrList(String str){
        List<String> list = new ArrayList<>(1);
        list.add(str);
        return list;
    }
}

package com.kauuze.app.include;


import java.util.Random;
import java.util.UUID;

/**
 * 随机数和毫秒值工具类
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class Rand {
    public static String getUUID(){
        return  UUID.randomUUID().toString().replace("-","");
    }

    public static long mill(){
        return System.currentTimeMillis();
    }

    private static final Random RANDOM = new Random(mill());


    /**
     *
     * @param count 获取几个数字
     * @return
     */
    public static int getNumber(int count){
        int num = 1;
        for(int i = 0 ;i < count;i++){
            num = num*10;
        }
        num = num/10 + RANDOM.nextInt(num - num/10);
        return num;
    }

    /**
     * 获取[0,max)的数
     * @param max
     * @return
     */
    public static int getRand(int max){
        return RANDOM.nextInt(max);
    }

    public static boolean getOnOff(){
        int result = getRand(2);
        if(result == 0){
            return true;
        }
            return false;
    }
    /**
     *
     * @param count 获取几个字符
     * @return
     */
    public static String getString(int count){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i < count;i++){
            if(getOnOff()){
                stringBuilder.append(getLetter());
            }else{
                stringBuilder.append(getRand(10));
            }
        }
        return stringBuilder.toString();
    }

    public static char getLetter (){
        int num = RANDOM.nextInt(26);
        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l'
                ,'m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        return chars[num];
    }
}

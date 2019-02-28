package com.kauuze.app.include;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于数据效验，数据为null通常为false
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class RU {
    /**
     * 用户名6-20位字母或数字下划线短横线,不能为纯数字
     */
    private static final String PATTERN_ACCOUNT = "^[a-zA-Z0-9_-]{6,20}$";
    /**
     * 英文状态字符8-32位，不能为纯数字
     */
    private static final String PATTERN_PASSWORD = "^[a-zA-Z0-9^%&',;=$\\x22\\(\\)\\[\\]\\|\\{\\}\\+\\-\\*!@#_/<>\\.:\\?]{8,32}$";
    /**
     * 手机验证码为6位数字
     */
    private static final String PATTERN_PHONE_CODE = "^[0-9]{6}$";

    /**
     * 图片验证码为6位字母或数字
     */
    private static final String PATTERN_PHOTO_CODE = "^[a-zA-Z0-9]{6}$";
    /**
     *真实姓名2-15个字
     */
    private static final String PATTERN_TRUE_NAME = "^[\\u4e00-\\u9fa5]{2,15}$";
    /**
     * 昵称，中日韩2两字节，字母数字下划线短横线一个字节,不允许纯数字。允许3-7个汉字(6--14个字符)
     */
    private static final String PATTERN_NICK_NAME = "6,14";

    /**
     * 英文状态字符
     */
    private static final String PATTERN_ENGLISH_CHARACTERS = "^[a-zA-Z0-9^%&',;=$\\x22\\(\\)\\[\\]\\|\\{\\}\\+\\-\\*!@#_/<>\\.:\\?]{1}$";

    private static final String PATTERN_URL ="^((ht|f)tps?):\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+([\\w\\-.,@?^=%&:\\/~+#]*[\\w\\-@?^=%&\\/~+#])?$";
    /**
     * 类型:text,rickHtml,jpg,jpeg,png,gif,mp3,mp4
     * rickHtml为富文本要求前端插入时做js-xss验证
     * @param infoViewType
     * @return
     */
    public static boolean isInfoViewType(String infoViewType){
        if(isEq(infoViewType,"text") || isEq(infoViewType,"rickHtml")|| isEq(infoViewType,"jpg") || isEq(infoViewType,"jpeg")|| isEq(infoViewType,"png") || isEq(infoViewType,"gif") || isEq(infoViewType,"mp4") || isEq(infoViewType,"mp3")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 用户名
     * @param account
     * @return
     */
    public static boolean isAccount(String account){
        if(isNumber(account)){
            return false;
        }
        if(patternReg(account, PATTERN_ACCOUNT)){
            return true;
        }
        return false;
    }


    /**
     * 是否是密码
     * @param password
     * @return
     */
    public static boolean isPassword(String password){
        if(patternReg(password, PATTERN_PASSWORD)){
            if(!isNumber(password)){
                return true;
            }
        }
        return false;
    }


    /**
     * 是否是手机验证码
     * @param msCode
     * @return
     */
    public static boolean isMsCode(Integer msCode){
        return patternReg(String.valueOf(msCode), PATTERN_PHONE_CODE);
    }

    /**
     *图片验证码
     * @param code
     * @return
     */
    public static boolean isPhotoCode(String code){
        return patternReg(code,PATTERN_PHOTO_CODE);
    }


    /**
     *昵称中日韩字母数字下划线短横线,且不能为纯数字
     * @param nickeName
     * @return
     */
    public static boolean isNickName(String nickeName){
        if(isEmpty(nickeName)){
            return false;
        }
        if(isNumber(nickeName)){
            return false;
        }
        String length = PATTERN_NICK_NAME;
        String[] strings = length.split(",");
        int min = Integer.valueOf(strings[0]);
        int max = Integer.valueOf(strings[1]);
        char[] chars = nickeName.toCharArray();
        int total = 0;
        for (char aChar : chars) {
            if(patternReg(String.valueOf(aChar),"[a-zA-Z0-9_-]")){
                total++;
            } else if(patternReg(String.valueOf(aChar),"[\\u4e00-\\u9fa5]|[\\x3130-\\x318F]|[\\xAC00-\\xD7A3]|[\\u0800-\\u4e00]")){
                total = total + 2;
            }else {
                return false;
            }
        }
        if(total < min || total > max){
            return false;
        }else{
            return true;
        }
    }


    //以下是固定规则

    /**
     * list不为空,不超过最大值返回true
     * @param list
     * @param max
     * @return
     */
    public static boolean listSizeMax(List list, int max){
        if(list == null){
            return false;
        }
        int size = list.size();
        if (size > max || size == 0){
            return false;
        }
        return true;
    }


    /**
     * 统计一共多少字符,字符占一位，中日韩等语言占两位
     * @param s
     * @return
     */
    public static int countTextCode(String s){
        if(isEmpty(s)){
            return 0;
        }
        int count = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if(patternReg(String.valueOf(aChar),PATTERN_ENGLISH_CHARACTERS)){
                count++;
            }else {
                count = count + 2;
            }
        }
        return count;
    }

    public static boolean isAge(Integer age){
        return patternReg(String.valueOf(age),"^((1[01][0-9])|(120)|([1-9][0-9])|([0-9]))$");
    }

    public static boolean isEmail(String email){
        if(email.length() > 250){
            return false;
        }
        return patternReg(email,"^[a-zA-Z0-9_.-]{1,}+@[a-zA-Z0-9-]{1,}+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");
    }

    public static boolean isPhone(String phone){
        if(!patternReg(phone,"^[1][0-9]{10}$")){
            return false;
        }
        char c = phone.charAt(0);
        if(c == '0' || c == '2' || c == '3'){
            return false;
        }
        return true;
    }

    public static boolean isSex(String sex){
        if(sex == null){
            return true;
        }

        if(isEq(sex,"男") || isEq(sex,"女")){
            return true;
        }
        return false;
    }

    /**
     * 1800-2200年
     * @param dateNumber
     * @return
     */
    public static boolean isDate(Integer dateNumber){
        String date = String.valueOf(dateNumber);
        if(date.length() != 8){
            return false;
        }
        String year = date.substring(0,4);
        String month = date.substring(4,6);
        String day = date.substring(6,8);
        if(isYear(year) && isMonth(month) && isDay(day)){
            return true;
        }
        return false;
    }
    /**
     * 1800-2200年
     * @param year
     * @return
     */
    public static boolean isYear(String year){
        if(year == null){
            return false;
        }
        if(Integer.valueOf(year) > 1800 && Integer.valueOf(year) < 2200){
            return true;
        }
        return false;
    }

    public static boolean isMonth(String month){
        if(patternReg(month,"^(01|02|03|04|04|05|07|08|09|10|11|12)$")){
            return true;
        }
        return false;
    }

    public static boolean isDay(String day){
        if(patternReg(day,"^(01|02|03|04|04|05|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31)$")){
            return true;
        }
        return false;
    }

    /**
     * 2002年-2100年
     * @param mill
     * @return
     */
    public static boolean isMill(Long mill){
        if(mill == null){
            return false;
        }
        if(mill < 1000000000000L || mill > 5000000000000L){
            return false;
        }
        return true;
    }

    /**
     * 身份证
     * @param idCard
     * @return
     */
    public static boolean isIdcard(String idCard){
        return patternReg(idCard,"^[1-9]\\d{5}(19|20)\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
    }

    /**
     * 中日韩占两位
     * @param str
     * @return
     */
    public static boolean strInfoMax(String str, int max){
        if(isEmpty(str)){
            return false;
        }
        if(countTextCode(str) > max){
            return false;
        }else{
            return true;
        }
    }

    /**
     * mysql id
     * @param id
     * @return
     */
    public static boolean isId(Integer id){
        if(id == null){
            return false;
        }
        if(id > 0 && id < 2000000000){
            return true;
        }
        return false;
    }

    public static boolean isUrl(String url){
        if(patternReg(url,PATTERN_URL)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isIp(String ip){
        if(isEmpty(ip)){
            return false;
        }
        String[] strs = ip.split("\\.");
        if(strs.length != 4 && strs.length != 6){
            return false;
        }
        for (String str : strs) {
            int num = Integer.valueOf(str);
            if(num > 255 || num < 0 ){
                return false;
            }
        }
        return true;
    }

    public static boolean isNumber(String str) {
        String regex = "^\\d+$";
        return patternReg(str, regex);
    }

    public static boolean isUUid(String uuid){
        if(RU.isEmpty(uuid)){
            return false;
        }
        if(patternReg(uuid,"^[a-zA-Z0-9]{32}$")){

            return true;
        }else{
            return false;
        }
    }

    /**
     *真实姓名
     * @param trueName
     * @return
     */
    public static boolean isTrueName(String trueName){
        return patternReg(trueName, PATTERN_TRUE_NAME);
    }

    /**
     * 判断字符串是否为空或空白字符
     * @param str 要判断的字符串
     * @return str为null或者空白字符返回true,否则返回false
     */
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str)){
            return true;
        }
        if(Pattern.matches("^\\s$", str)){
            return true;
        }
        return false;
    }

    /**
     * 用于valid验证，处理空值是否通过验证
     * @param o
     * @param require
     * @return
     */
    public static boolean validRequire(Object o,boolean require){
        if(!require){
            if(o == null){
                return true;
            }
            try {
                if(RU.isEmpty(String.valueOf(o))){
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    /**
     *如有为null返回false
     * @return s1.equals(s2)
     */
    public static boolean isEq(String value, String value2){
        if(value == null || value2 == null) {
            return false;
        }
        return value.equals(value2);
    }

    /**
     * 如果有null返回false
     * @param value
     * @param value2
     * @return
     */
    public static boolean notEq(String value,String value2){
        return !isEq(value,value2);
    }

    /**
     * 两个包装类型Number是否相等
     * @param value
     * @param value2
     * @return
     */
    public static boolean isEq(Integer value, Integer value2){
        if(value == null || value2 == null){
            return false;
        }
        return value.intValue() == value2.intValue();
    }

    /**
     * 两个Integer,是否前一个比后一个大
     * @return
     */
    public static boolean isIntegerGt(Integer value,Integer value2){
        if(value == null || value2 == null){
            return false;
        }
        return value.intValue() > value2.intValue();
    }

    /**
     * 两个Integer,是否前一个比后一个大或相等
     * @param value
     * @param value2
     * @return
     */
    public static boolean isIntegerGtOrEq(Long value,Long value2){
        if(value == null || value2 == null){
            return false;
        }
        return value.longValue() >= value2.longValue();
    }

    /**
     * 两个Long,是否前一个比后一个大
     * @return
     */
    public static boolean isLongGt(Long value,Long value2){
        if(value == null || value2 == null){
            return false;
        }
        return value.longValue() > value2.longValue();
    }

    /**
     * 两个Long,是否前一个比后一个大
     * @return
     */
    public static boolean isLongEq(Long value,Long value2){
        if(value == null || value2 == null){
            return false;
        }
        return value.longValue() > value2.longValue();
    }

    /**
     * 两个Long,是否前一个比后一个大或相等
     * @param value
     * @param value2
     * @return
     */
    public static boolean isLongGtOrEq(Long value,Long value2){
        if(value == null || value2 == null){
            return false;
        }
        return value.longValue() >= value2.longValue();
    }

    /**
     * 字符串为空返回false
     * @param str
     * @param reg
     * @return
     */
    private static boolean patternReg(String str,String reg){
        if(isEmpty(str) || isEmpty(reg)){
            return false;
        }
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        boolean rs = matcher.matches();
        return rs;
    }
}

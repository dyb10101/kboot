package com.kauuze.app.especiallyutil;


import com.kauuze.app.include.BoxUtil;
import com.kauuze.app.include.HttpUtils;
import com.kauuze.app.include.RU;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 发送短信验证码工具类
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class MsCodeSendUtil {
    private static final String url = "http://yzx.market.alicloudapi.com/yzx/sendSms";
    private static final String appcode = "";
    private static final String tp1 = "TP18032912";
    private static final HttpHeaders headers = new HttpHeaders();
    static {
        List<String> list = new ArrayList<>();
        list.add("APPCODE " + appcode);
        headers.put("Authorization", list);
    }
    /**
     *
     * @param phone 发送给手机号
     * @param code 发送的验证码
     * @return  false发送失败,true发送成功
     */
    public static boolean sendTp1(String phone,int code){
        if(RU.isEmpty(appcode)){
            System.out.println("(未设置MsCodeSendUtil.appcode)手机号 " + phone + " 的短信验证码: " + code);
            return true;
        }
        Map<String, String> bodys = new HashMap<String, String>();
        MultiValueMap<String,String> query = new LinkedMultiValueMap();
        query.put("mobile", BoxUtil.putStrList(phone));
        query.put("param", BoxUtil.putStrList("code:" + code));
        query.put("tpl_id", BoxUtil.putStrList(tp1));
        return doSend(query,null);
    }


    private static boolean doSend(MultiValueMap query,MultiValueMap body){
        String response = HttpUtils.post(url,query,body,headers);
        Map<String,String> map = BoxUtil.parseJsonString(response,Map.class);
        String returnCode = map.get("return_code");
        if(RU.isEq(returnCode,"0000")){
            return true;
        }else {
            return false;
        }
    }
}


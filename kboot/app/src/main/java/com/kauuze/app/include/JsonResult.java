package com.kauuze.app.include;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
@Data
@AllArgsConstructor
public class JsonResult {
    private String state;
    private Object data;
    private String message;


    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult("success",null,null);
        return jsonResult;
    }
    public static JsonResult success(Object data){
        JsonResult jsonResult = new JsonResult("success",data,null);
        return jsonResult;
    }
    public static JsonResult success(Object data,String message){
        JsonResult jsonResult = new JsonResult("success",data,message);
        return jsonResult;
    }


    public static JsonResult failure(){
        JsonResult jsonResult = new JsonResult("failure",null,null);
        return jsonResult;
    }
    public static JsonResult failure(String message){
        JsonResult jsonResult = new JsonResult("failure",null,message);
        return jsonResult;
    }

    public static JsonResult failure(String message,Object data){
        JsonResult jsonResult = new JsonResult("failure",data,message);
        return jsonResult;
    }

    /**
     * 参数验证错误
     * @return
     */
    public static JsonResult mismatch(){
        JsonResult jsonResult = new JsonResult("mismatch",null,"please check the request parameters.");
        return jsonResult;
    }

}

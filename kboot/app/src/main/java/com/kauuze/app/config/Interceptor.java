package com.kauuze.app.config;


import com.kauuze.app.include.annotation.permission.Authorization;
import com.kauuze.app.include.annotation.permission.GreenWay;
import com.kauuze.app.include.annotation.permission.Speak;
import com.kauuze.app.include.annotation.permission.Vip;
import com.kauuze.app.domain.sso.dao.TokenDao;
import com.kauuze.app.domain.sso.entity.Token;
import com.kauuze.app.include.BoxUtil;
import com.kauuze.app.include.RU;
import com.kauuze.app.include.Rand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器用于权限验证
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class Interceptor implements HandlerInterceptor {
    /**
     * 接口安全码,用于接口转移
     */
    private static final String requestKey = "kauuze";
    @Autowired
    private TokenDao tokenDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //绿色通道放行
        GreenWay greenWay = handlerMethod.getMethod().getAnnotation(GreenWay.class);
        if(greenWay != null){
            return true;
        }

        if(RU.notEq(request.getMethod(),"POST")){
            response401(response,"Please submit method by POST");
            return false;
        }
        //验证是否经过网关过滤
        String requestKey2 = request.getHeader("requestKey");
        if(RU.notEq(requestKey,requestKey2)){
            response401(response,"you have to signature key");
            return false;
        }

        Authorization authorization = handlerMethod.getMethod().getAnnotation(Authorization.class);
        Speak speak = handlerMethod.getMethod().getAnnotation(Speak.class);
        Vip vip = handlerMethod.getMethod().getAnnotation(Vip.class);
        //不需要验证
        if(authorization == null && speak == null && vip == null){
            return true;
        }
        String authorization2 = request.getHeader("Authorization");
        if(RU.isEmpty(authorization2)){
            response401(response,"Authorization is null");
            return false;
        }
        int uid = 0;
        try {
            uid = Integer.valueOf(Token.cutAuthUid(authorization2));
        } catch (Exception e) {
            response401(response,"Authorization is format error");
            return false;
        }
        if(!RU.isId(uid)){
            response401(response,"Authorization is format error");
            return false;
        }
        Token token = tokenDao.findByUid(uid);
        if(token == null){
            response401(response,"Authorization uid is not exist");
            return false;
        }
        if(RU.notEq(token.getAuthorization(),authorization2)){
            response401(response,"Authorization is mismatch");
            return false;
        }
        long mill = Rand.mill();
        String state = token.getState();
        Long stateEndTime = token.getStateEndTime();
        String role = token.getRole();
        Long roleEndTime = token.getRoleEndTime();
        if(RU.isEq(role,"manager")){
            request.setAttribute("uid",uid);
            return true;
        }

        if(stateEndTime != null && stateEndTime <= mill){
            state = "normal";
        }
        if(roleEndTime != null && roleEndTime <= mill){
            role = "user";
        }

        if(RU.isEq(state,"ban")){
            if(stateEndTime == null){
                response403(response,"ban");
                return false;
            }
            response403(response,"ban_" + BoxUtil.millToView(stateEndTime) + "_cause:" + token.getStateCause());
            return false;
        }

        //被禁言
        if(speak != null && RU.isEq(state,"shutup")){
            if(stateEndTime == null){
                response403(response,"shutup");
                return false;
            }
            response403(response,"shutup_" + BoxUtil.millToView(stateEndTime) + "_cause:" + token.getStateCause());
            return false;
        }
        //不是vip
        if(vip != null && RU.notEq(role,"vip")){
            response403(response,"not vip");
            return false;
        }
        request.setAttribute("uid",uid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Content-type", "application/json;charset=utf-8");
    }

    /**
     * 验证未通过
     * @param response
     * @param msg
     */
    private void response401(HttpServletResponse response,String msg){
        try {
            response.setHeader("Content-type", "text/plain;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(msg);
        } catch (IOException e) {
            throw new RuntimeException("Interceptor response401 error");
        }
    }

    /**
     * 验证通过但权限不足
     * @param response
     * @param msg
     */
    private void response403(HttpServletResponse response,String msg){
        try {
            response.setHeader("Content-type", "text/plain;charset=UTF-8");
            response.setStatus(403);
            response.getWriter().write(msg);
        } catch (IOException e) {
            throw new RuntimeException("Interceptor response403 error");
        }
    }
}

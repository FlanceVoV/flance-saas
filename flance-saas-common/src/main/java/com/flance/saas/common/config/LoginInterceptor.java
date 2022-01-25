package com.flance.saas.common.config;

import com.flance.saas.common.login.BaseLogin;
import com.flance.saas.common.utils.LogUtil;
import com.flance.saas.common.utils.TokenUtil;
import com.flance.web.utils.RedisUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登录拦截器
 * @author jhf
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        token = "test";
        if (null == token) {
            return false;
        }
        String userInfo = redisUtils.get(token);
//        userInfo = "{\"tenantId\":\"201\",\"tenantSchema\":\"flance_schema_test\",\"tenantSuffix\":\"01\"}";
        userInfo = "{\"tenantId\":\"201\"}";
        // 租户表权限校验 tenant.getTables;
        if (null == userInfo) {
            return false;
        }
        Gson gson = new Gson();
        TokenUtil.putLogin(gson.fromJson(userInfo, BaseLogin.class));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        try {
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            LogUtil.removeLogId();
            TokenUtil.removeLogin();
        }
    }
}

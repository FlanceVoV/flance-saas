package com.flance.saas.common.config;

import com.flance.saas.common.core.AuthService;
import com.flance.saas.common.utils.LogUtil;
import com.flance.saas.common.utils.LoginUtil;
import com.flance.web.utils.RedisUtils;
import com.flance.web.utils.RequestConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SysInterceptor implements HandlerInterceptor {

    @Resource
    RedisUtils redisUtils;

    @Resource
    AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(RequestConstant.HEADER_TOKEN);
        String userId = request.getHeader(RequestConstant.HEADER_USER_ID);
        String key = RequestConstant.SYS_TOKEN_KEY + userId;
        String userInfo = redisUtils.get(key + ":" + token);
        String requestId = request.getHeader(RequestConstant.HEADER_REQUEST_ID);
        if (null != userInfo) {
            redisUtils.setExp(key + ":" + token, 7200L);
            LoginUtil.putLogin(userInfo);
        }
        if (!authService.checkUserAuth(requestId)) {
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
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
            LoginUtil.removeLogin();
        }
    }
}

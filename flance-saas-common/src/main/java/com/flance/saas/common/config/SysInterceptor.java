package com.flance.saas.common.config;

import com.flance.saas.common.core.AuthService;
import com.flance.saas.common.utils.LogUtil;
import com.flance.saas.common.utils.LoginUtil;
import com.flance.web.feign.FeignUser;
import com.flance.web.utils.RedisUtils;
import com.flance.web.utils.RequestConstant;
import com.flance.web.utils.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
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
        String userType = request.getHeader(RequestConstant.HEADER_USER_TYPE);
        String key = RequestConstant.SYS_TOKEN_KEY + userType + ":" + userId;
        String userInfo = redisUtils.get(key + ":" + token);
        String requestId = request.getHeader(RequestConstant.HEADER_REQUEST_ID);
        String uri = request.getRequestURI();
        String feignUser = request.getHeader(FeignUser.HEADER_FEIGN_USER);
        String feignPassword = request.getHeader(FeignUser.HEADER_FEIGN_PASS);
        log.info("tenant sys interceptor token:[{}] userId:[{}] userInfo:[{}] requestId:[{}] requestUri:[{}]", token, userId, userInfo, requestId, uri);
        boolean isFeign = authService.checkFeign(feignUser, feignPassword);
        if (isFeign) {
            log.info("tenant feign feignUser:[{}] feignPassword:[{}]",  feignUser, feignPassword);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        if (!StringUtils.hasLength(requestId)) {
            requestId = uri;
        }
        if (null != userInfo) {
            redisUtils.setExp(key + ":" + token, 7200L);
            LoginUtil.putLogin(userInfo);
        }
        if (!authService.checkUserAuth(requestId)) {
            if (null == userInfo) {
                throw new AuthException("系统鉴权 -- token失效 请重新登录 ！", "400001");
            }
            throw new AuthException("系统鉴权 无权限访问！", "400003");
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

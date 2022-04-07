package com.flance.saas.common.config;

import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.common.core.TenantCacheModel;
import com.flance.saas.common.login.TenantChooseModel;
import com.flance.saas.common.utils.LogUtil;
import com.flance.saas.common.utils.LoginUtil;
import com.flance.saas.common.utils.TenantChooseUtil;
import com.flance.web.utils.GsonUtils;
import com.flance.web.utils.RedisUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 租户应用拦截器
 * @author jhf
 */
@Component
public class TenantAppInterceptor implements HandlerInterceptor {

    @Resource
    RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String userId = request.getHeader("userId");
        String tenantId = request.getHeader("tenantId");
        String key = SaasConstant.SYS_TOKEN_KEY + userId;
        String userInfo = redisUtils.get(key + ":" + token);

        // 租户表权限校验 tenant.getTables;
        if (null == userInfo) {
            return false;
        }
        LoginUtil.putLogin(userInfo);

        String tenantCache = redisUtils.get(SaasConstant.SYS_TENANT_KEY + tenantId);
        if (null == tenantCache) {
            return true;
        }
        TenantCacheModel tenantCacheModel = GsonUtils.fromString(tenantCache, TenantCacheModel.class);

        TenantChooseModel tenantChooseModel = new TenantChooseModel();
        tenantChooseModel.setTenantId(tenantId);
        tenantChooseModel.setUserId(userId);
        tenantChooseModel.setToken(token);
        tenantChooseModel.setTenantSchema(tenantCacheModel.getTenantSchema());
        tenantChooseModel.setTenantSuffix(tenantCacheModel.getTenantSuffix());
        tenantChooseModel.setTables(tenantCacheModel.getTables());


        TenantChooseUtil.putTenantLogin(tenantChooseModel);
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
            LoginUtil.removeLogin();
            TenantChooseUtil.removeTenantLogin();
        }
    }
}

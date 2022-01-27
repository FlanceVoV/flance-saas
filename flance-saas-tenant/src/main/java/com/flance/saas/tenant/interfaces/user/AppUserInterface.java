package com.flance.saas.tenant.interfaces.user;

import com.flance.saas.tenant.domain.user.domain.AppUserDomain;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUserApp;
import com.flance.saas.tenant.domain.user.service.AppUserService;
import com.flance.saas.tenant.infrastructure.LoginUtil;
import com.flance.saas.tenant.infrastructure.SaasConstant;
import com.flance.web.utils.GsonUtils;
import com.flance.web.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * app用户服务
 * @author jhf
 */
@Service
public class AppUserInterface {

    @Resource
    AppUserService appUserService;

    @Resource
    RedisUtils redisUtils;

    /**
     * 登录
     * @param userAccount   账户
     * @param userPassword  密码.md5
     * @return              返回用户信息
     */
    public LoginUserApp login(String userAccount, String userPassword) {
        AppUserEntity appUserEntity = new AppUserEntity();
        appUserEntity.setUserAccount(userAccount);
        appUserEntity.setUserPassword(userPassword);
        AppUserDomain appUserDomain = AppUserDomain.builder()
                .appUserEntity(appUserEntity)
                .appUserService(appUserService)
                .build();
        LoginUserApp loginUserApp = appUserDomain.login();
        String userInfo = GsonUtils.toJSONString(loginUserApp);
        String key = SaasConstant.SAAS_APP_LOGIN_TOKEN_KEY + loginUserApp.getUserId();
        LoginUtil.loginSet(key, redisUtils);
        redisUtils.add(key + ":" + loginUserApp.getToken(), userInfo, SaasConstant.SAAS_USER_EXP_TIME);
        return loginUserApp;
    }


}

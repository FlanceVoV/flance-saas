package com.flance.saas.tenant.interfaces.user;

import com.flance.saas.tenant.domain.user.domain.AppUserDomain;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.saas.tenant.domain.user.service.AppUserService;
import com.flance.saas.tenant.infrastructure.LoginUtil;
import com.flance.saas.common.core.SaasConstant;
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
    public LoginUser login(String userAccount, String userPassword) {
        AppUserEntity appUserEntity = new AppUserEntity();
        appUserEntity.setUserAccount(userAccount);
        appUserEntity.setUserPassword(userPassword);
        AppUserDomain appUserDomain = AppUserDomain.builder()
                .appUserEntity(appUserEntity)
                .appUserService(appUserService)
                .build();
        LoginUser loginUser = appUserDomain.login();
        loginUser.setUserType("app");
        String userInfo = GsonUtils.toJSONString(loginUser);
        String key = SaasConstant.SYS_TOKEN_KEY + loginUser.getUserId();
        LoginUtil.loginSet(key, redisUtils);
        redisUtils.add(key + ":" + loginUser.getToken(), userInfo, SaasConstant.SAAS_USER_EXP_TIME);
        return loginUser;
    }


}

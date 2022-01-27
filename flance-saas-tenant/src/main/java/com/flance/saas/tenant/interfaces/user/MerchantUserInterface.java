package com.flance.saas.tenant.interfaces.user;

import com.flance.saas.tenant.domain.user.domain.AppUserDomain;
import com.flance.saas.tenant.domain.user.domain.MerchantUserDomain;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUserApp;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUserMerchant;
import com.flance.saas.tenant.domain.user.service.AppUserService;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import com.flance.saas.tenant.infrastructure.LoginUtil;
import com.flance.saas.tenant.infrastructure.SaasConstant;
import com.flance.web.utils.GsonUtils;
import com.flance.web.utils.RedisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * app用户服务
 * @author jhf
 */
@Service
public class MerchantUserInterface {

    @Resource
    MerchantUserService merchantUserService;

    @Resource
    RedisUtils redisUtils;

    /**
     * 登录
     * @param userAccount   账户
     * @param userPassword  密码.md5
     * @return              返回用户信息
     */
    public LoginUserMerchant login(String userAccount, String userPassword) {
        MerchantUserEntity merchantUserEntity = new MerchantUserEntity();
        merchantUserEntity.setUserAccount(userAccount);
        merchantUserEntity.setUserPassword(userPassword);
        MerchantUserDomain appUserDomain = MerchantUserDomain.builder()
                .merchantUserEntity(merchantUserEntity)
                .merchantUserService(merchantUserService)
                .build();
        LoginUserMerchant loginUserMerchant = appUserDomain.login();
        String userInfo = GsonUtils.toJSONString(loginUserMerchant);
        String key = SaasConstant.SAAS_MERCHANT_LOGIN_TOKEN_KEY + loginUserMerchant.getUserId();
        LoginUtil.loginSet(key, redisUtils);
        redisUtils.add(key + ":" + loginUserMerchant.getToken(), userInfo, SaasConstant.SAAS_USER_EXP_TIME);
        return loginUserMerchant;
    }


}

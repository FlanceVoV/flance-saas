package com.flance.saas.tenant.interfaces.user;

import com.flance.saas.common.utils.LoginUtil;
import com.flance.saas.tenant.domain.user.domain.MerchantUserDomain;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.web.utils.GsonUtils;
import com.flance.web.utils.RedisUtils;
import com.flance.web.utils.RequestConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public LoginUser login(String userAccount, String userPassword) {
        MerchantUserEntity merchantUserEntity = new MerchantUserEntity();
        merchantUserEntity.setUserAccount(userAccount);
        merchantUserEntity.setUserPassword(userPassword);
        MerchantUserDomain appUserDomain = MerchantUserDomain.builder()
                .merchantUserEntity(merchantUserEntity)
                .merchantUserService(merchantUserService)
                .build();
        LoginUser loginUser = appUserDomain.login();
        loginUser.setUserType("merchant");
        String userInfo = GsonUtils.toJSONString(loginUser);
        String key = RequestConstant.SYS_TOKEN_KEY + loginUser.getUserId();
        LoginUtil.loginSet(key, redisUtils);
        redisUtils.add(key + ":" + loginUser.getToken(), userInfo, SaasConstant.SAAS_USER_EXP_TIME);
        return loginUser;
    }


}

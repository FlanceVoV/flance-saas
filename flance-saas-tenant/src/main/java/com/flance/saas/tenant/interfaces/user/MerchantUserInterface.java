package com.flance.saas.tenant.interfaces.user;

import com.flance.saas.common.utils.LoginUtil;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
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
import java.util.List;

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
        MerchantUserDomain merchantUserDomain = MerchantUserDomain.builder()
                .merchantUserEntity(merchantUserEntity)
                .merchantUserService(merchantUserService)
                .build();
        LoginUser loginUser = merchantUserDomain.login();
        loginUser.setUserType(SaasConstant.SYS_USER_TYPE_MERCHANT);
        String userInfo = GsonUtils.toJSONString(loginUser);
        String key = RequestConstant.SYS_TOKEN_KEY + SaasConstant.SYS_USER_TYPE_MERCHANT + ":" + loginUser.getUserId();
        LoginUtil.loginSet(key, redisUtils);
        redisUtils.add(key + ":" + loginUser.getToken(), userInfo, SaasConstant.SAAS_USER_EXP_TIME);
        return loginUser;
    }

    public List<MenuEntity> getTenantUserMenu(String userId, String tenantId) {
        return merchantUserService.getUserMenu(userId, tenantId);
    }

    public List<RoleEntity> getTenantUserRole(String userId, String tenantId) {
        return merchantUserService.getUserRole(userId, tenantId);
    }

    public List<AuthEntity> getTenantUserAuth(String userId, String tenantId) {
        return merchantUserService.getUserAuth(userId, tenantId);
    }

    public void registerMerchantUser(MerchantUserEntity entity) {
        MerchantUserDomain merchantUserDomain = MerchantUserDomain.builder()
                .merchantUserEntity(entity)
                .merchantUserService(merchantUserService)
                .build();
        merchantUserDomain.register();
    }

}

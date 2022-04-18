package com.flance.saas.tenant.domain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;

import java.util.List;

public interface MerchantUserService extends IService<MerchantUserEntity> {

    MerchantUserEntity loginForOpenId(String openId);

    MerchantUserEntity loginForPassword(String userAccount, String userPassword);

    List<Tenant> getMerchantUserTenants(String appUserId);

    List<MenuEntity> getUserMenu(String userId, String tenantId);

    List<RoleEntity> getUserRole(String userId, String tenantId);

    List<AuthEntity> getUserAuth(String userId, String tenantId);

    String encodePassword(String userAccount, String userPassword);

}

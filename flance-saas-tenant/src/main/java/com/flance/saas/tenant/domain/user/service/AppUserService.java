package com.flance.saas.tenant.domain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;

import java.util.List;

public interface AppUserService extends IService<AppUserEntity> {

    AppUserEntity loginForOpenId(String openId);

    AppUserEntity loginForPassword(String userAccount, String userPassword);

    List<Tenant> getAppUserTenants(String appUserId);

    void setUserMenu(AppUserEntity appUserEntity, String tenantId);

    void setUserRole(AppUserEntity appUserEntity, String tenantId);

    String encodePassword(String userAccount, String userPassword);

    List<String> findRoleIds(String userId, String tenantId);

}

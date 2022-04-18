package com.flance.saas.tenant.domain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;

import java.util.List;

public interface AppUserService extends IService<AppUserEntity> {

    AppUserEntity loginForOpenId(String openId);

    AppUserEntity loginForPassword(String userAccount, String userPassword);

    List<Tenant> getAppUserTenants(String appUserId);

    List<MenuEntity> getUserMenu(String userId, String tenantId);

    List<RoleEntity> getUserRole(String userId, String tenantId);

    String encodePassword(String userAccount, String userPassword);

}

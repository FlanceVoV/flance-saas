package com.flance.saas.tenant.domain.user.service;

import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.base.IUser;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;

import java.util.List;


public interface BaseUserService {

    List<MenuEntity> getUserMenu(String userId, String tenantId);

    List<AuthEntity> getUserAuth(String userId, String tenantId);

    List<RoleEntity> getUserRole(String userId, String tenantId);

    void setUserMenu(IUser user, String userId, String tenantId);

    void setUserAuth(IUser user, String userId, String tenantId);

    void setUserRole(IUser user, String userId, String tenantId);
}

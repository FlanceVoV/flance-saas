package com.flance.saas.tenant.domain.user.service;

import com.flance.saas.db.annotation.Column;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.base.IUser;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


public interface BaseUserService {

    List<Tenant> getTenantUserTenants(String userId);

    List<MenuEntity> getUserMenu(String userId, String tenantId, String userType);

    List<AuthEntity> getUserAuth(String userId, String tenantId, String userType);

    List<RoleEntity> getUserRole(String userId, String tenantId, String userType);

    void setUserMenu(IUser user, String userId, String tenantId, String userType);

    void setUserAuth(IUser user, String userId, String tenantId, String userType);

    void setUserRole(IUser user, String userId, String tenantId, String userType);

}

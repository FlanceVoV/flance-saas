package com.flance.saas.tenant.domain.base;

import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;

import java.util.List;

public interface IUser {

    void setUserRoles(List<RoleEntity> roles);

    void setUserAuths(List<AuthEntity> auths);

    void setUserMenus(List<MenuEntity> menus);
}


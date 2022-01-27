package com.flance.saas.tenant.domain.user.domain.vo;

import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginUserSys {

    private String userId;

    private String token;

    private String userAccount;

    private String userName;

    private String userPhone;

    private String authConfig;

    private String menuConfig;

    private String roleConfig;

    private List<String> auths;

    private List<MenuEntity> userMenus;

    private List<RoleEntity> userRoles;

    public String getAuthConfig() {
        if (null != auths) {

        }
        return authConfig;
    }

    public String getRoleConfig() {
        if (null != userRoles) {

        }
        return roleConfig;
    }

    public String getMenuConfig() {
        if (null != userMenus) {

        }
        return menuConfig;
    }

}

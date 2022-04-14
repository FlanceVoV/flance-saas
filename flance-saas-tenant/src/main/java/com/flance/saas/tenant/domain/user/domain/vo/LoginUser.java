package com.flance.saas.tenant.domain.user.domain.vo;

import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginUser {

    private String userId;

    private String token;

    private String userType;

    private String userAccount;

    private String userName;

    private String userPhone;

    private String userNickName;

    private String userImages;

    private String userIdNum;

    private String authConfig;

    private String menuConfig;

    private String roleConfig;

    private List<String> auths;

    private List<Tenant> userTenants;

    private List<MenuEntity> userMenus;

    private List<RoleEntity> userRoles;

    public List<String> getTenants() {
        List<String> tenants = Lists.newArrayList();
        if (null != userTenants) {
            userTenants.forEach(item -> tenants.add(item.getTenantId()));
        }
        return tenants;
    }

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

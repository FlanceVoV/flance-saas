package com.flance.saas.tenant.domain.user.domain.vo;

import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
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

    private String ip;

    private List<String> auths;

    private List<Tenant> userTenants;

    private List<MenuEntity> userMenus;

    private List<RoleEntity> userRoles;

    private List<AuthEntity> userAuths;

    public List<String> getTenants() {
        List<String> tenants = Lists.newArrayList();
        if (null != userTenants) {
            userTenants.forEach(item -> tenants.add(item.getTenantId()));
        }
        return tenants;
    }

    public String getAuthConfig() {
        StringBuilder sb = new StringBuilder();
        if (null != userAuths) {
            userAuths.forEach(auth -> sb.append(auth.getAuthCode()).append(","));
        }
        String result = sb.toString();
        return result.length() > 0 ? result.substring(0, result.length() - 1) : null;
    }

    public String getRoleConfig() {
        StringBuilder sb = new StringBuilder();
        if (null != userRoles) {
            userRoles.forEach(role -> sb.append(role.getRoleCode()).append(","));
        }
        String result = sb.toString();
        return result.length() > 0 ? result.substring(0, result.length() - 1) : null;
    }

    public String getMenuConfig() {
        StringBuilder sb = new StringBuilder();
        if (null != userMenus) {
            userMenus.forEach(menu -> sb.append(menu.getMenuCode()).append(","));
        }
        String result = sb.toString();
        return result.length() > 0 ? result.substring(0, result.length() - 1) : null;
    }

}

package com.flance.saas.tenant.domain.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.auth.service.AuthorityService;
import com.flance.saas.tenant.domain.base.IUser;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.service.RoleAuthService;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import com.flance.saas.tenant.domain.role.service.RoleService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.saas.tenant.domain.user.service.BaseUserService;
import com.flance.saas.tenant.domain.user.service.UserRoleService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Resource
    private AuthorityService authorityService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private RoleAuthService roleAuthService;

    @Resource
    private TenantService tenantService;

    @Override
    public List<Tenant> getTenantUserTenants(String userId) {
        return tenantService.getAppUserTenant(userId);
    }

    @Override
    public void setUserMenu(IUser user, String userId, String tenantId, String userType) {
        user.setUserMenus(getUserMenu(userId, tenantId, userType));
    }

    @Override
    public void setUserAuth(IUser user, String userId, String tenantId, String userType) {
        user.setUserAuths(getUserAuth(userId, tenantId, userType));
    }

    @Override
    public void setUserRole(IUser user, String userId, String tenantId, String userType) {
        user.setUserRoles(getUserRole(userId, tenantId, userType));
    }

    @Override
    public List<MenuEntity> getUserMenu(String userId, String tenantId, String userType) {
        List<String> menuIds = roleMenuService.findMenuIds(userRoleService.findRoleIds(userId, tenantId, userType));
        if (null == menuIds || menuIds.size() == 0) {
            return Lists.newArrayList();
        }
        menuIds = menuIds.stream().distinct().collect(Collectors.toList());
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SaasConstant.DATA_USER_TYPE_NAME, userType);
        queryWrapper.in(SaasConstant.DATA_ID_NAME, menuIds);
        queryWrapper.in(!StringUtils.isEmpty(tenantId), SaasConstant.DATA_TENANT_ID_NAME, tenantId);
        return menuService.list(queryWrapper);
    }

    @Override
    public List<AuthEntity> getUserAuth(String userId, String tenantId, String userType) {
        List<String> authIds = roleAuthService.findAuthIds(userRoleService.findRoleIds(userId, tenantId, userType));
        if (null == authIds || authIds.size() == 0) {
            return Lists.newArrayList();
        }
        authIds = authIds.stream().distinct().collect(Collectors.toList());
        QueryWrapper<AuthEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SaasConstant.DATA_USER_TYPE_NAME, userType);
        queryWrapper.in(SaasConstant.DATA_ID_NAME, authIds);
        queryWrapper.in(!StringUtils.isEmpty(tenantId), SaasConstant.DATA_TENANT_ID_NAME, tenantId);
        return authorityService.list(queryWrapper);
    }

    @Override
    public List<RoleEntity> getUserRole(String userId, String tenantId, String userType) {
        List<String> roleIds = userRoleService.findRoleIds(userId, tenantId, userType);
        if (null == roleIds || roleIds.size() == 0) {
            return Lists.newArrayList();
        }
        roleIds = roleIds.stream().distinct().collect(Collectors.toList());
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SaasConstant.DATA_USER_TYPE_NAME, userType);
        queryWrapper.in(SaasConstant.DATA_ID_NAME, roleIds);
        queryWrapper.in(!StringUtils.isEmpty(tenantId), SaasConstant.HEADER_TENANT_ID, tenantId);
        return roleService.list(queryWrapper);
    }
}

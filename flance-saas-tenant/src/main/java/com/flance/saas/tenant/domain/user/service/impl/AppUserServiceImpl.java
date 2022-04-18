package com.flance.saas.tenant.domain.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.mapper.AppUserMapper;
import com.flance.saas.tenant.domain.user.service.AppUserService;
import com.flance.saas.tenant.domain.user.service.BaseUserService;
import com.flance.saas.tenant.domain.user.service.UserRoleService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppUserServiceImpl extends BaseService<String, AppUserMapper, AppUserEntity> implements AppUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private BaseUserService baseUserService;

    @Override
    public AppUserEntity loginForOpenId(String openId) {
        return null;
    }

    @Override
    public AppUserEntity loginForPassword(String userAccount, String userPassword) {
        LambdaQueryWrapper<AppUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AppUserEntity::getUserAccount, userAccount);
        AppUserEntity found = this.getOne(lambdaQueryWrapper);
        AssertUtil.notNull(found, AssertException.getNormal("找不到用户，请确认账户是否输入正确[" + userAccount + "]", "-1"));
        String foundPassword = found.getUserPassword();
        boolean flag = passwordEncoder.matches(userAccount + userPassword, foundPassword);
        AssertUtil.mastTrue(flag, AssertException.getNormal("密码不正确，请确认后再尝试", "-1"));
        return found;
    }

    @Override
    public List<Tenant> getAppUserTenants(String appUserId) {
        return baseUserService.getTenantUserTenants(appUserId);
    }

    @Override
    public List<MenuEntity> getUserMenu(String userId, String tenantId) {
        return baseUserService.getUserMenu(userId, tenantId);
    }

    @Override
    public List<RoleEntity> getUserRole(String userId, String tenantId) {
        return baseUserService.getUserRole(userId, tenantId);
    }

    @Override
    public List<AuthEntity> getUserAuth(String userId, String tenantId) {
        return baseUserService.getUserAuth(userId, tenantId);
    }

    @Override
    public String encodePassword(String userAccount, String userPassword) {
        return passwordEncoder.encode(userAccount + userPassword);
    }


}

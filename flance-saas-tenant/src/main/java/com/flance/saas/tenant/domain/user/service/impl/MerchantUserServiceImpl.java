package com.flance.saas.tenant.domain.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.menu.domain.MenuDomain;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import com.flance.saas.tenant.domain.role.domain.RoleDomain;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.service.RoleService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.domain.entity.UserRoleEntity;
import com.flance.saas.tenant.domain.user.mapper.MerchantUserMapper;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import com.flance.saas.tenant.domain.user.service.UserRoleService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantUserServiceImpl extends BaseService<String, MerchantUserMapper, MerchantUserEntity> implements MerchantUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private TenantService tenantService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Override
    public MerchantUserEntity loginForOpenId(String openId) {
        return null;
    }

    @Override
    public MerchantUserEntity loginForPassword(String userAccount, String userPassword) {
        LambdaQueryWrapper<MerchantUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MerchantUserEntity::getUserAccount, userAccount);
        lambdaQueryWrapper.eq(MerchantUserEntity::getStatus, SaasConstant.DATA_STATUS_NORMAL);
        MerchantUserEntity found = this.getOne(lambdaQueryWrapper);
        AssertUtil.notNull(found, AssertException.getNormal("找不到用户，请确认账户是否输入正确[" + userAccount + "]", "-1"));
        String foundPassword = found.getUserPassword();
        boolean flag = passwordEncoder.matches(userAccount + userPassword, foundPassword);
        AssertUtil.mastTrue(flag, AssertException.getNormal("密码不正确，请确认后再尝试", "-1"));
        return found;
    }

    @Override
    public List<Tenant> getAppUserTenants(String appUserId) {
        return tenantService.getAppUserTenant(appUserId);
    }

    @Override
    public void setUserMenu(MerchantUserEntity merchantUserEntity, String tenantId) {
        MenuEntity menuEntity = new MenuEntity();
        List<String> menuIds = roleService.findMenuIds(findRoleIds(merchantUserEntity.getId(), tenantId));
        if (null == menuIds || menuIds.size() == 0) {
            merchantUserEntity.setUserMenus(Lists.newArrayList());
            return;
        }
        menuEntity.setIds(menuIds);
        menuEntity.setTenantId(tenantId);
        MenuDomain menuDomain = MenuDomain.builder()
                .menuEntity(menuEntity)
                .menuService(menuService)
                .build();
        merchantUserEntity.setUserMenus(menuDomain.list("IN_id_ids", "EQ_tenantId_tenantId"));
    }

    @Override
    public void setUserRole(MerchantUserEntity merchantUserEntity, String tenantId) {
        RoleEntity roleEntity = new RoleEntity();
        List<String> roleIds = findRoleIds(merchantUserEntity.getId(), tenantId);
        if (null == roleIds || roleIds.size() == 0) {
            merchantUserEntity.setUserRoles(Lists.newArrayList());
            return;
        }
        roleEntity.setIds(roleIds);
        roleEntity.setTenantId(tenantId);
        RoleDomain roleDomain = RoleDomain.builder()
                .roleEntity(roleEntity)
                .roleService(roleService)
                .build();
        merchantUserEntity.setUserRoles(roleDomain.list("IN_id_ids", "EQ_tenantId_tenantId"));
    }

    @Override
    public String encodePassword(String userAccount, String userPassword) {
        return passwordEncoder.encode(userAccount + userPassword);
    }

    @Override
    public List<String> findRoleIds(String userId, String tenantId) {
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRoleEntity::getUserId, userId);
        queryWrapper.eq(UserRoleEntity::getStatus, SaasConstant.DATA_STATUS_NORMAL);
        List<UserRoleEntity> list = userRoleService.list(queryWrapper);
        List<String> roleIds = Lists.newArrayList();
        list.forEach(item -> roleIds.add(item.getRoleId()));
        return roleIds.stream().distinct().collect(Collectors.toList());
    }
}

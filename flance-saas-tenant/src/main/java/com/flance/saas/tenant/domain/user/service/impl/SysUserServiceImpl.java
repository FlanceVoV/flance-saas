package com.flance.saas.tenant.domain.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.menu.domain.MenuDomain;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import com.flance.saas.tenant.domain.role.domain.RoleDomain;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.service.RoleService;
import com.flance.saas.tenant.domain.user.domain.entity.SysUserEntity;
import com.flance.saas.tenant.domain.user.domain.entity.UserRoleEntity;
import com.flance.saas.tenant.domain.user.mapper.SysUserMapper;
import com.flance.saas.tenant.domain.user.service.SysUserService;
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
public class SysUserServiceImpl extends BaseService<String, SysUserMapper, SysUserEntity> implements SysUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public SysUserEntity login(String userAccount, String userPassword) {
        LambdaQueryWrapper<SysUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserEntity::getUserAccount, userAccount);
        SysUserEntity found = this.getOne(lambdaQueryWrapper);
        AssertUtil.notNull(found, AssertException.getNormal("找不到用户，请确认账户是否输入正确[" + userAccount + "]", "-1"));
        String foundPassword = found.getUserPassword();
        boolean flag = passwordEncoder.matches(userAccount + userPassword, foundPassword);
        AssertUtil.mastTrue(flag, AssertException.getNormal("密码不正确，请确认后再尝试", "-1"));
        return found;
    }

    @Override
    public List<String> findRoleIds(String userId) {
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRoleEntity::getUserId, userId);
        List<UserRoleEntity> list = userRoleService.list(queryWrapper);
        List<String> roleIds = Lists.newArrayList();
        list.forEach(item -> roleIds.add(item.getRoleId()));
        return roleIds.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void setUserMenu(SysUserEntity sysUserEntity) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setIds(roleService.findMenuIds(findRoleIds(sysUserEntity.getId())));
        MenuDomain menuDomain = MenuDomain.builder()
                .menuEntity(menuEntity)
                .menuService(menuService)
                .build();
        sysUserEntity.setUserMenus(menuDomain.list("IN_ids"));
    }

    @Override
    public void setUserRole(SysUserEntity sysUserEntity) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setIds(findRoleIds(sysUserEntity.getId()));
        RoleDomain roleDomain = RoleDomain.builder()
                .roleEntity(roleEntity)
                .roleService(roleService)
                .build();
        sysUserEntity.setUserRoles(roleDomain.list("IN_ids"));
    }

    @Override
    public String encodePassword(String userAccount, String userPassword) {
        return passwordEncoder.encode(userAccount + userPassword);
    }
}

package com.flance.saas.tenant.domain.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleMenuEntity;
import com.flance.saas.tenant.domain.role.mapper.RoleMapper;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import com.flance.saas.tenant.domain.role.service.RoleService;
import com.flance.saas.tenant.domain.user.domain.entity.UserRoleEntity;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends BaseService<String, RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public List<String> findMenuIds(List<String> roleIds) {
        if (null == roleIds || roleIds.size() == 0) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<RoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenuEntity::getStatus, SaasConstant.DATA_STATUS_NORMAL);
        queryWrapper.in(RoleMenuEntity::getRoleId, roleIds);
        List<RoleMenuEntity> roleMenuEntities = roleMenuService.list(queryWrapper);
        List<String> menuIds = Lists.newArrayList();
        roleMenuEntities.forEach(item -> menuIds.add(item.getMenuId()));
        return menuIds.stream().distinct().collect(Collectors.toList());
    }
}

package com.flance.saas.tenant.domain.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleMenuEntity;
import com.flance.saas.tenant.domain.role.mapper.RoleMenuMapper;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuServiceImpl extends BaseService<String, RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {

    @Override
    public List<String> findMenuIds(List<String> roleIds) {
        if (null == roleIds || roleIds.size() == 0) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<RoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RoleMenuEntity::getRoleId, roleIds);
        List<RoleMenuEntity> roleMenuEntities = list(queryWrapper);
        List<String> menuIds = Lists.newArrayList();
        roleMenuEntities.forEach(item -> menuIds.add(item.getMenuId()));
        return menuIds.stream().distinct().collect(Collectors.toList());
    }

}

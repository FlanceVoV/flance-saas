package com.flance.saas.tenant.domain.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleAuthEntity;
import com.flance.saas.tenant.domain.role.mapper.RoleAuthMapper;
import com.flance.saas.tenant.domain.role.service.RoleAuthService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleAuthServiceImpl extends BaseService<String, RoleAuthMapper, RoleAuthEntity> implements RoleAuthService {


    @Override
    public List<String> findAuthIds(List<String> roleIds) {
        if (null == roleIds || roleIds.size() == 0) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<RoleAuthEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(RoleAuthEntity::getRoleId, roleIds);
        List<RoleAuthEntity> roleMenuEntities = list(queryWrapper);
        List<String> menuIds = Lists.newArrayList();
        roleMenuEntities.forEach(item -> menuIds.add(item.getAuthId()));
        return menuIds.stream().distinct().collect(Collectors.toList());
    }


}

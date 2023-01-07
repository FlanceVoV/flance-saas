package com.flance.saas.tenant.domain.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.user.domain.entity.UserRoleEntity;
import com.flance.saas.tenant.domain.user.mapper.UserRoleMapper;
import com.flance.saas.tenant.domain.user.service.UserRoleService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl extends BaseService<String, UserRoleMapper, UserRoleEntity> implements UserRoleService {

    @Override
    public List<String> findRoleIds(String userId, String tenantId, String userType) {
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRoleEntity::getUserId, userId);
        queryWrapper.eq(UserRoleEntity::getUserType, userType);
        queryWrapper.eq(StringUtils.hasLength(tenantId), UserRoleEntity::getTenantId, tenantId);
        List<UserRoleEntity> list = list(queryWrapper);
        List<String> roleIds = Lists.newArrayList();
        list.forEach(item -> roleIds.add(item.getRoleId()));
        return roleIds.stream().distinct().collect(Collectors.toList());
    }

}

package com.flance.saas.tenant.domain.role.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.mapper.RoleMapper;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import com.flance.saas.tenant.domain.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl extends BaseService<String, RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;




}

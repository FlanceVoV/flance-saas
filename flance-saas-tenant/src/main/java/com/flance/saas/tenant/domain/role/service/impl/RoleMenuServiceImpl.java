package com.flance.saas.tenant.domain.role.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleMenuEntity;
import com.flance.saas.tenant.domain.role.mapper.RoleMenuMapper;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends BaseService<String, RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {
}

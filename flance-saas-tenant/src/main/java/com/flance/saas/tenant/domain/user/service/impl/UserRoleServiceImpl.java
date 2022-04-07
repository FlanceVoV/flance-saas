package com.flance.saas.tenant.domain.user.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.user.domain.entity.UserRoleEntity;
import com.flance.saas.tenant.domain.user.mapper.UserRoleMapper;
import com.flance.saas.tenant.domain.user.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends BaseService<String, UserRoleMapper, UserRoleEntity> implements UserRoleService {


}

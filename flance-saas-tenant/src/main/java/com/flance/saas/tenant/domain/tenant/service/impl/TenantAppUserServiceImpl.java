package com.flance.saas.tenant.domain.tenant.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantAppUser;
import com.flance.saas.tenant.domain.tenant.mapper.TenantAppUserMapper;
import com.flance.saas.tenant.domain.tenant.service.TenantAppUserService;
import org.springframework.stereotype.Service;

@Service
public class TenantAppUserServiceImpl extends BaseService<String, TenantAppUserMapper, TenantAppUser> implements TenantAppUserService {
}

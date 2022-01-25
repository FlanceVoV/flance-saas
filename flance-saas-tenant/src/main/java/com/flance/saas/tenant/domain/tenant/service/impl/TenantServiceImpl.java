package com.flance.saas.tenant.domain.tenant.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.tenant.mapper.TenantMapper;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl extends BaseService<String, TenantMapper, Tenant> implements TenantService {




}

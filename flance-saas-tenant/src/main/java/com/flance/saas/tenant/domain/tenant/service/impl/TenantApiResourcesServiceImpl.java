package com.flance.saas.tenant.domain.tenant.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantApiResources;
import com.flance.saas.tenant.domain.tenant.mapper.TenantApiResourcesMapper;
import com.flance.saas.tenant.domain.tenant.service.TenantApiResourcesService;
import org.springframework.stereotype.Service;

@Service
public class TenantApiResourcesServiceImpl extends BaseService<String, TenantApiResourcesMapper, TenantApiResources> implements TenantApiResourcesService {
}

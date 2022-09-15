package com.flance.saas.tenant.domain.tenant.service;

import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;

public interface TenantFacadeService {

    Tenant getByTenantId(String tenantId);

}

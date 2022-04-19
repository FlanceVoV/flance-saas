package com.flance.saas.tenant.domain.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;

import java.util.List;

public interface TenantService extends IService<Tenant> {

    List<Tenant> getAppUserTenant(String appUserId);

    List<Tenant> getMerchantUserTenant(String merchantUserId);

    void register(Tenant tenant);

}

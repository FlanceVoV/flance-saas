package com.flance.saas.tenant.domain.tenant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantFacadeService;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TenantFacadeServiceImpl implements TenantFacadeService {

    @Resource
    private TenantService tenantService;

    @Override
    public Tenant getByTenantId(String tenantId) {
        LambdaQueryWrapper<Tenant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tenant::getTenantId, tenantId);
        return tenantService.getOne(queryWrapper);
    }
}

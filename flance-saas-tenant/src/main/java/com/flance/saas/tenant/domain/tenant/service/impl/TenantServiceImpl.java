package com.flance.saas.tenant.domain.tenant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantAppUser;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantMerchantUser;
import com.flance.saas.tenant.domain.tenant.mapper.TenantMapper;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantAppUserService;
import com.flance.saas.tenant.domain.tenant.service.TenantMerchantUserService;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TenantServiceImpl extends BaseService<String, TenantMapper, Tenant> implements TenantService {

    @Resource
    TenantAppUserService tenantAppUserService;

    @Resource
    TenantMerchantUserService tenantMerchantUserService;

    @Override
    public List<Tenant> getAppUserTenant(String appUserId) {
        LambdaQueryWrapper<TenantAppUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantAppUser::getUserId, appUserId);
        List<TenantAppUser> list = tenantAppUserService.list(queryWrapper);
        List<String> tenantIds = Lists.newLinkedList();
        list.forEach(appUser -> tenantIds.add(appUser.getTenantId()));
        List<Tenant> result = getTenants(tenantIds);
        return null == result ? Lists.newArrayList() : result;
    }

    @Override
    public List<Tenant> getMerchantUserTenant(String merchantUserId) {
        LambdaQueryWrapper<TenantMerchantUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantMerchantUser::getUserId, merchantUserId);
        List<TenantMerchantUser> list = tenantMerchantUserService.list(queryWrapper);
        List<String> tenantIds = Lists.newLinkedList();
        list.forEach(merchantUser -> tenantIds.add(merchantUser.getTenantId()));
        List<Tenant> result = getTenants(tenantIds);
        return null == result ? Lists.newArrayList() : result;
    }

    private List<Tenant> getTenants(List<String> tenantIds) {
        LambdaQueryWrapper<Tenant> tenantQueryWrapper = new LambdaQueryWrapper<>();
        tenantQueryWrapper.in(Tenant::getId, tenantIds);
        return list(tenantQueryWrapper);
    }
}

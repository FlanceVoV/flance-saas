package com.flance.saas.tenant.domain.tenant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;
import com.flance.saas.tenant.domain.table.service.SchemaFacadeService;
import com.flance.saas.tenant.domain.table.service.SchemaService;
import com.flance.saas.tenant.domain.table.service.SchemaTableService;
import com.flance.saas.tenant.domain.table.service.TableService;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantApiResources;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantAppUser;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantMerchantUser;
import com.flance.saas.tenant.domain.tenant.mapper.TenantMapper;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantApiResourcesService;
import com.flance.saas.tenant.domain.tenant.service.TenantAppUserService;
import com.flance.saas.tenant.domain.tenant.service.TenantMerchantUserService;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.google.common.collect.Lists;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl extends BaseService<String, TenantMapper, Tenant> implements TenantService {

    @Resource
    SchemaFacadeService schemaFacadeService;

    @Resource
    TenantAppUserService tenantAppUserService;

    @Resource
    TenantMerchantUserService tenantMerchantUserService;

    @Resource
    TenantApiResourcesService tenantApiResourcesService;


    @Override
    public List<Tenant> getAppUserTenant(String appUserId) {
        LambdaQueryWrapper<TenantAppUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantAppUser::getUserId, appUserId);
        List<TenantAppUser> list = tenantAppUserService.list(queryWrapper);
        List<String> tenantIds = Lists.newArrayList();
        list.forEach(appUser -> tenantIds.add(appUser.getTenantId()));
        List<Tenant> result = getTenants(tenantIds);
        return null == result ? Lists.newArrayList() : result;
    }

    @Override
    public List<Tenant> getMerchantUserTenant(String merchantUserId) {
        LambdaQueryWrapper<TenantMerchantUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantMerchantUser::getUserId, merchantUserId);
        List<TenantMerchantUser> list = tenantMerchantUserService.list(queryWrapper);
        List<String> tenantIds = Lists.newArrayList();
        list.forEach(merchantUser -> tenantIds.add(merchantUser.getTenantId()));
        List<Tenant> result = getTenants(tenantIds);
        return null == result ? Lists.newArrayList() : result;
    }

    private List<Tenant> getTenants(List<String> tenantIds) {
        LambdaQueryWrapper<Tenant> tenantQueryWrapper = new LambdaQueryWrapper<>();
        tenantQueryWrapper.in(Tenant::getId, tenantIds);
        return list(tenantQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(Tenant tenant) {
        SchemaEntity schemaEntity = schemaFacadeService.getSchemaById(tenant.getSchemaId());
        AssertUtil.notNull(schemaEntity, AssertException.getNormal("非法请求！找不到schema[" + tenant.getSchemaId() + "]", "-1"));
        save(tenant);
        List<TenantApiResources> apis = Lists.newArrayList();
        Optional.ofNullable(tenant.getApis()).orElse(Lists.newArrayList()).forEach(apiId -> {
            TenantApiResources api = new TenantApiResources();
            api.setApiId(apiId);
            api.setTenantId(tenant.getId());
            api.setCreateDate(new Date());
            api.setStatus(SaasConstant.DATA_STATUS_NORMAL);
            api.setCreateUserId(tenant.getCreateUserId());
            apis.add(api);
        });
        Tenant tenantFound = getById(tenant.getId());
        AssertUtil.notNull(tenantFound, AssertException.getNormal("非法请求，tenant不存在", "-1"));
        tenantApiResourcesService.saveBatch(apis);
        schemaFacadeService.createInstance(schemaEntity, tenantFound);
    }
}

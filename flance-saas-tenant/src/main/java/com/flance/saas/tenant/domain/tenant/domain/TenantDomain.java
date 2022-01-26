package com.flance.saas.tenant.domain.tenant.domain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 租户领域模型
 * @author jhf
 */
@Data
@Builder
public class TenantDomain {

    /**
     * 租户
     */
    @NonNull
    private Tenant tenant;

    /**
     * 租户服务
     */
    @NonNull
    private TenantService tenantService;

    /**
     * 创建租户
     */
    public void create() {
        tenantService.save(this.tenant);
    }

    /**
     * 修改租户
     */
    public void updateById() {
        AssertUtil.notEmpty(tenant.getId(), AssertException.getNormal("唯一标识条件[id]不允许为空", "-1"));
        tenantService.updateById(this.tenant);
    }

    /**
     * 删除租户
     */
    public void deleteById() {
        AssertUtil.notEmpty(tenant.getId(), AssertException.getNormal("唯一标识条件[id]不允许为空", "-1"));
        Tenant waitUpdate = new Tenant();
        waitUpdate.setId(tenant.getId());
        waitUpdate.setStatus(0);
        tenantService.updateById(waitUpdate);
    }

    /**
     * 列表查询
     * @return list
     */
    public List<Tenant> list() {
        LambdaQueryWrapper<Tenant> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Tenant::getStatus, 1);
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(tenant.getTenantName()), Tenant::getTenantName, tenant.getTenantName());
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(tenant.getTenantId()), Tenant::getTenantId, tenant.getTenantId());
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(tenant.getTenantSchema()), Tenant::getTenantSchema, tenant.getTenantSchema());
       return tenantService.list(lambdaQueryWrapper);
    }



    /**
     * 根据主键查询
     * @return  返回tenant实例
     */
    public Tenant get() {
        LambdaQueryWrapper<Tenant> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        AssertUtil.notEmpty(tenant.getId(), AssertException.getNormal("唯一标识查询条件[id]不允许为空", "-1"));
        lambdaQueryWrapper.eq(Tenant::getId, tenant.getId());
        lambdaQueryWrapper.eq(Tenant::getStatus, 1);
        return tenantService.getOne(lambdaQueryWrapper);
    }

    /**
     * 根据唯一标识查询
     * @return  返回tenant实例
     */
    public Tenant getByUnique() {
        LambdaQueryWrapper<Tenant> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        AssertUtil.notEmpty(tenant.getTenantId(), AssertException.getNormal("唯一标识查询条件[tenantId]不允许为空", "-1"));
        lambdaQueryWrapper.eq(Tenant::getTenantId, tenant.getTenantId());
        lambdaQueryWrapper.eq(Tenant::getStatus, 1);
        return tenantService.getOne(lambdaQueryWrapper);
    }

}

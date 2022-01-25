package com.flance.saas.tenant.domain.tenant.domain;

import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

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
        tenantService.updateById(this.tenant);
    }

    /**
     * 列表查询
     * @return list
     */
    public List<Tenant> list() {
       return tenantService.list();
    }

}

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



}

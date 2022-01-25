package com.flance.saas.tenant.domain.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantMapper extends BaseMapper<Tenant> {
}

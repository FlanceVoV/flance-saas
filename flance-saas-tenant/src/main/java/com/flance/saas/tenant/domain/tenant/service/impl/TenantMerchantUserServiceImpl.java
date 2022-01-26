package com.flance.saas.tenant.domain.tenant.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantMerchantUser;
import com.flance.saas.tenant.domain.tenant.mapper.TenantMerchantUserMapper;
import com.flance.saas.tenant.domain.tenant.service.TenantMerchantUserService;
import org.springframework.stereotype.Service;

@Service
public class TenantMerchantUserServiceImpl extends BaseService<String, TenantMerchantUserMapper, TenantMerchantUser> implements TenantMerchantUserService {

}

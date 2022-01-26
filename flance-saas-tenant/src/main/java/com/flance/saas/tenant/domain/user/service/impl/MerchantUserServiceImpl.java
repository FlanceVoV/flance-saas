package com.flance.saas.tenant.domain.user.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.mapper.MerchantUserMapper;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import org.springframework.stereotype.Service;

@Service
public class MerchantUserServiceImpl extends BaseService<String, MerchantUserMapper, MerchantUserEntity> implements MerchantUserService {

}

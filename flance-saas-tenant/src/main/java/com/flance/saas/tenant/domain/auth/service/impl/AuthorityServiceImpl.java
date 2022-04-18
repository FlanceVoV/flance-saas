package com.flance.saas.tenant.domain.auth.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.auth.mapper.AuthMapper;
import com.flance.saas.tenant.domain.auth.service.AuthorityService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl extends BaseService<String, AuthMapper, AuthEntity> implements AuthorityService {


}

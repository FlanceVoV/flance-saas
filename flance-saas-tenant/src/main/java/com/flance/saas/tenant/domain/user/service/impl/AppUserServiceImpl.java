package com.flance.saas.tenant.domain.user.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.mapper.AppUserMapper;
import com.flance.saas.tenant.domain.user.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends BaseService<String, AppUserMapper, AppUserEntity> implements AppUserService {

}

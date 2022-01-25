package com.flance.saas.tenant.domain.user.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.user.domain.entity.User;
import com.flance.saas.tenant.domain.user.mapper.UserMapper;
import com.flance.saas.tenant.domain.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService<String, UserMapper, User> implements UserService {

}

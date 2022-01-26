package com.flance.saas.tenant.domain.menu.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.mapper.MenuMapper;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends BaseService<String, MenuMapper, MenuEntity> implements MenuService {


}

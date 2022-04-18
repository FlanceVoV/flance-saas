package com.flance.saas.tenant.domain.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleMenuEntity;
import com.flance.saas.tenant.domain.role.mapper.RoleMapper;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import com.flance.saas.tenant.domain.role.service.RoleService;
import com.flance.saas.tenant.domain.user.domain.entity.UserRoleEntity;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends BaseService<String, RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;



    /*

    List<MenuEntity> getUserMenu(String userId);

    List<MenuEntity> getUserMenu(String userId, String tenantId);
     */


}

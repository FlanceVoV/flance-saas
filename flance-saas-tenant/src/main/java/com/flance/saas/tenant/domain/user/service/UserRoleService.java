package com.flance.saas.tenant.domain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.user.domain.entity.UserRoleEntity;

import java.util.List;

public interface UserRoleService extends IService<UserRoleEntity> {

    List<String> findRoleIds(String userId, String tenantId);

    List<String> findRoleIds(String userId);

}

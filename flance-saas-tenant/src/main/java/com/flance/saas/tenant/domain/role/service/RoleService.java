package com.flance.saas.tenant.domain.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;

import java.util.List;


public interface RoleService extends IService<RoleEntity> {

    List<String> findMenuIds(List<String> roleIds);

}

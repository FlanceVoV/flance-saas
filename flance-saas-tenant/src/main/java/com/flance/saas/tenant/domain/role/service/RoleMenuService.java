package com.flance.saas.tenant.domain.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleMenuEntity;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenuEntity> {

    List<String> findMenuIds(List<String> roleIds);

}

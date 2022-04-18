package com.flance.saas.tenant.domain.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.role.domain.entity.RoleAuthEntity;

import java.util.List;

public interface RoleAuthService extends IService<RoleAuthEntity> {

    List<String> findAuthIds(List<String> roleIds);

}

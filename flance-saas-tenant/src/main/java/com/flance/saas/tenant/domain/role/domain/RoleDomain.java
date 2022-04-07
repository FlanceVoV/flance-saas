package com.flance.saas.tenant.domain.role.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.base.BaseDomain;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.service.RoleService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class RoleDomain extends BaseDomain<String, RoleEntity> {

    @NonNull
    private RoleEntity roleEntity;

    @NonNull
    private RoleService roleService;

    @Override
    public String createId() {
        return IdUtil.fastSimpleUUID();
    }
}

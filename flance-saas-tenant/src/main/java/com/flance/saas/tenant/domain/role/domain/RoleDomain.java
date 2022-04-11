package com.flance.saas.tenant.domain.role.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.base.BaseDomain;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.service.RoleService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class RoleDomain extends BaseDomain<String, RoleEntity> {

    @NonNull
    private RoleEntity roleEntity;

    @NonNull
    private RoleService roleService;

    public RoleDomain(@NonNull RoleEntity roleEntity, @NonNull RoleService roleService) {
        this.roleEntity = roleEntity;
        this.roleService = roleService;
        setInfo(roleEntity, roleService);
    }

    @Override
    public String createId() {
        return IdUtil.fastSimpleUUID();
    }
}

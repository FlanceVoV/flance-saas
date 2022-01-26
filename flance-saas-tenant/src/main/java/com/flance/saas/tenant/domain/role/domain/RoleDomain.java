package com.flance.saas.tenant.domain.role.domain;

import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.role.service.RoleService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class RoleDomain {

    @NonNull
    private RoleEntity roleEntity;

    @NonNull
    private RoleService roleService;



}

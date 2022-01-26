package com.flance.saas.tenant.domain.menu.domain;

import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class MenuDomain {

    @NonNull
    private MenuEntity menuEntity;

    @NonNull
    private MenuService menuService;

    public List<MenuEntity> list() {
        return null;
    }

}

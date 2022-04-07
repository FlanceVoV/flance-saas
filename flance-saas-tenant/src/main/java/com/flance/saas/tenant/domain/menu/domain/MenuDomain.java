package com.flance.saas.tenant.domain.menu.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.base.BaseDomain;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;



@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class MenuDomain extends BaseDomain<String, MenuEntity> {

    @NonNull
    private MenuEntity menuEntity;

    @NonNull
    private MenuService menuService;

    public MenuDomain(@NonNull MenuEntity menuEntity, @NonNull MenuService menuService) {
        this.menuEntity = menuEntity;
        this.menuService = menuService;
        setInfo(menuEntity, menuService);
    }

    @Override
    public String createId() {
        return IdUtil.fastSimpleUUID();
    }
}

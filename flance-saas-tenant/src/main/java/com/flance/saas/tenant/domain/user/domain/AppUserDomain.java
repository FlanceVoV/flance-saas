package com.flance.saas.tenant.domain.user.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.base.BaseDomain;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.saas.tenant.domain.user.service.AppUserService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;


@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AppUserDomain extends BaseDomain<String, AppUserEntity> {

    @NonNull
    private AppUserService appUserService;

    @NonNull
    private AppUserEntity appUserEntity;

    public AppUserDomain(@NonNull AppUserService appUserService, @NonNull AppUserEntity appUserEntity) {
        this.appUserService = appUserService;
        this.appUserEntity = appUserEntity;
        setInfo(appUserEntity, appUserService);
    }

    public LoginUser login() {
        AppUserEntity logon = appUserService.loginForPassword(appUserEntity.getUserAccount(), appUserEntity.getUserPassword());
        String token = IdUtil.fastSimpleUUID();
        appUserService.setLastLoginIp(logon.getId());
        return LoginUser.builder()
                .userTenants(appUserService.getAppUserTenants(logon.getId()))
                .userAccount(logon.getUserAccount())
                .token(token)
                .build();
    }

    public LoginUser loginForOpenId() {
        return LoginUser.builder().build();
    }

    @Override
    public String createId() {
        return IdUtil.fastSimpleUUID();
    }
}

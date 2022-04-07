package com.flance.saas.tenant.domain.user.domain;

import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.saas.tenant.domain.user.service.AppUserService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AppUserDomain {

    @NonNull
    private AppUserService appUserService;

    @NonNull
    private AppUserEntity appUserEntity;

    public LoginUser login() {

        return null;
    }

}

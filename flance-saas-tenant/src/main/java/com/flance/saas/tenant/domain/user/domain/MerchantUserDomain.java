package com.flance.saas.tenant.domain.user.domain;

import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUserApp;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUserMerchant;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MerchantUserDomain {

    @NonNull
    private MerchantUserService merchantUserService;

    @NonNull
    private MerchantUserEntity merchantUserEntity;

    public LoginUserMerchant login() {

        return null;
    }


}

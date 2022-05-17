package com.flance.saas.tenant.domain.user.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
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

    public LoginUser login() {
        MerchantUserEntity logon = merchantUserService.loginForPassword(merchantUserEntity.getUserAccount(), merchantUserEntity.getUserPassword());
        String token = IdUtil.fastSimpleUUID();
        merchantUserService.setLastLoginIp(logon.getId());
        return LoginUser.builder()
                .userAccount(logon.getUserAccount())
                .userTenants(merchantUserService.getMerchantUserTenants(logon.getId()))
                .token(token)
                .build();
    }


}

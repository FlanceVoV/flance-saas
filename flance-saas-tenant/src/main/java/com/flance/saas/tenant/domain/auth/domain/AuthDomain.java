package com.flance.saas.tenant.domain.auth.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.base.BaseDomain;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class AuthDomain extends BaseDomain<String, AuthEntity> {

    @NonNull
    private AuthEntity authEntity;

    @Override
    public String createId() {
        return IdUtil.fastSimpleUUID();
    }
}

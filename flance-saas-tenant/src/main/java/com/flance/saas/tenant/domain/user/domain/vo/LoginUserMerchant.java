package com.flance.saas.tenant.domain.user.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserMerchant {

    private String userId;

    private String token;


}

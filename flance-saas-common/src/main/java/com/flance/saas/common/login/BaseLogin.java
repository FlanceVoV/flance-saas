package com.flance.saas.common.login;

import lombok.Data;

@Data
public class BaseLogin {

    private String userId;

    private String token;

    private String tenantId;

    private String tenantSchema;

    private String tenantSuffix;

}

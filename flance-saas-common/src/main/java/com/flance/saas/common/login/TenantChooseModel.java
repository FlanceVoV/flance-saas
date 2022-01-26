package com.flance.saas.common.login;

import lombok.Data;

@Data
public class TenantChooseModel {

    private String userId;

    private String token;

    private String tenantId;

    private String tenantSchema;

    private String tenantSuffix;

}

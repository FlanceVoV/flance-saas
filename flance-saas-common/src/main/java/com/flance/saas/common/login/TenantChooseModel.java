package com.flance.saas.common.login;

import lombok.Data;

import java.util.List;

@Data
public class TenantChooseModel {

    private String userId;

    private String token;

    private String tenantId;

    private String tenantSchema;

    private String tenantSuffix;

    private List<String> tables;

}

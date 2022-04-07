package com.flance.saas.common.core;

import lombok.Data;

import java.util.List;

@Data
public class TenantCacheModel {

    private String tenantId;

    private String tenantSchema;

    private String tenantSuffix;

    private List<String> tables;

}

package com.flance.saas.tenant.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantRegisterResponse {

    private String tenantId;

    private String tenantQRCode;

}

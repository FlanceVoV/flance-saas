package com.flance.saas.tenant.domain.vo;

import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Valid
public class TenantRegisterRequest {

    /**
     * 租户名称
     */
    @NotBlank
    private String tenantName;

    /**
     * 租户简介
     */
    @NotBlank
    private String tenantDesc;

    /**
     * 租户应用图标
     */
    private String tenantIcon;

    /**
     * 租户密钥
     */
    @NotBlank
    private String secretKey;

    /**
     * 租户域名
     */
    @NotBlank
    private String domain;

    /**
     * 是否启用
     * 1.启用 0.禁用
     */
    @NotNull
    private Integer enabled;

    /**
     * 是否开放注册
     */
    @NotNull
    private Integer isOpen;

    /**
     * 应用库
     */
    @NotBlank
    private String schemaId;

    public Tenant parseTenant() {
        Tenant entity = new Tenant();
        entity.setTenantName(this.tenantName);
        entity.setSchemaId(this.schemaId);
        entity.setDomain(this.domain);
        entity.setEnabled(this.enabled);
        entity.setSecretKey(this.secretKey);
        entity.setTenantIcon(this.tenantIcon);
        return entity;
    }

}

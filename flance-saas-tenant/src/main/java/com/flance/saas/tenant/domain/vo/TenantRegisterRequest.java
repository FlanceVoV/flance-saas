package com.flance.saas.tenant.domain.vo;

import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import lombok.Data;

import java.util.List;

@Data
public class TenantRegisterRequest {

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 租户简介
     */
    private String tenantDesc;

    /**
     * 租户应用图标
     */
    private String tenantIcon;

    /**
     * 租户密钥
     */
    private String secretKey;

    /**
     * 租户域名
     */
    private String domain;

    /**
     * 是否启用
     * 1.启用 0.禁用
     */
    private Integer enabled;

    /**
     * 是否开放注册
     */
    private Integer isOpen;

    /**
     * 应用库
     */
    private String schemaId;

    /**
     * 开通接口权益业务
     */
    private List<String> rights;

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

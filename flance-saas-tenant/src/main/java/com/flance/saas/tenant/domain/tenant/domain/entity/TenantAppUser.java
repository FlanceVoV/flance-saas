package com.flance.saas.tenant.domain.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 外部用户
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_tenant_app_user", indexes = {
        @Index(columns = {"user_id", "tenant_id"}, indexName = "un_user_tenant_id", indexType = Index.IndexType.UNIQUE),
})
@TableName("sys_flance_saas_tenant_app_user")
public class TenantAppUser extends BaseTable {

    @Column(notNull = true)
    private String userId;

    @Column(notNull = true)
    private String tenantId;

    @Column(notNull = true)
    private Integer enabled;

    @Column(notNull = true)
    private String menuConfig;

    @Column(notNull = true)
    private String authConfig;

    @TableField(exist = false)
    private Tenant tenant;

}

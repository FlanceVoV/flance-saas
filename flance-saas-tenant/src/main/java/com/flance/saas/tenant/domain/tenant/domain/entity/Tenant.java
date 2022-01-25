package com.flance.saas.tenant.domain.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.ITable;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 租户模型
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_tenant", indexes = {
        @Index(columns = {"tenant_id"}, indexName = "un_tenant_id", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"user_id", "tenant_name"}, indexName = "idx_user_id_tenant_name"),
        @Index(columns = {"tenant_schema"}, indexName = "idx_tenant_schema")
})
@TableName("sys_flance_saas_tenant")
public class Tenant extends BaseTable {

    /**
     * 租户唯一标识
     */
    @Column(notNull = true)
    private String tenantId;

    /**
     * 用户id，即该租户属于哪个用户
     */
    @Column(notNull = true)
    private String userId;

    /**
     * 租户名称
     */
    @Column(notNull = true)
    private String tenantName;

    /**
     * 租户公钥
     */
    @Column(notNull = true)
    private String pubKey;

    /**
     * 租户域名
     */
    @Column(notNull = true)
    private String domain;

    /**
     * 表空间
     */
    @Column(notNull = true)
    private String tenantSchema;

    /**
     * 租户后缀，用于区分表名后缀
     */
    @Column(notNull = true)
    private String tenantSuffix;

    /**
     * 租户所拥有的业务表实例
     */
    @TableField(exist = false)
    private List<ITable> tables;

}

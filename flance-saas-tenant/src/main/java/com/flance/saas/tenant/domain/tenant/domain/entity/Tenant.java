package com.flance.saas.tenant.domain.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.ITable;
import com.flance.saas.db.tables.common.BaseTable;
import com.google.common.collect.Lists;
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
     * 租户表实例，开通的业务表
     */
    @Column(notNull = true, length = "4000")
    private String openTables;

    /**
     * 是否启用
     * 1.启用 0.禁用
     */
    @Column(notNull = true)
    private Integer enabled;

    /**
     * 是否开放注册
     */
    @Column(notNull = true)
    private Integer isOpen;

    /**
     * 租户所拥有的业务表实例
     */
    @TableField(exist = false)
    private List<ITable> tables;

    /**
     * 租户所拥有的业务表代码
     */
    @TableField(exist = false)
    private List<String> tableNames;

    /**
     * 获取业务表实例
     */
    public List<ITable> getTables() {
        List<ITable> tables = Lists.newArrayList();
        if (null != openTables) {
            List<String> tableEntityNames = Lists.newArrayList(openTables.split(","));
            tableEntityNames.forEach(entityName -> {
                try {
                    ITable table = (ITable) Class.forName(entityName).newInstance();
                    tables.add(table);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return tables;
    }

    /**
     * 获取所有业务表
     */
    public List<String> getTableNames() {
        List<String> tableNames = Lists.newArrayList();
        if (null != openTables) {
            tableNames = Lists.newArrayList(openTables.split(","));
        }
        return tableNames;
    }
}

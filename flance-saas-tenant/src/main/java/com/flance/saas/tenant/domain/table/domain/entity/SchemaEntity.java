package com.flance.saas.tenant.domain.table.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 应用业务封装
 * 用户表空间
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_schema", indexes = {
        @Index(columns = {"schema_name"}, indexName = "un_schema_name", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"schema_unique_code"}, indexName = "un_schema_unique_code", indexType = Index.IndexType.UNIQUE),
})
@TableName("sys_flance_saas_schema")
public class SchemaEntity extends BaseTable {

    @Column(notNull = true)
    private String schemaName;

    @Column(notNull = true)
    private String schemaCNName;

    @Column(notNull = true)
    private String schemaDesc;

    @Column(notNull = true)
    private String schemaUniqueCode;

    @TableField(exist = false)
    private List<TableEntity> tables;

}

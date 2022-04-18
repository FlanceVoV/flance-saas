package com.flance.saas.tenant.domain.table.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_schema_table", indexes = {
        @Index(columns = {"table_id","schema_id"}, indexName = "un_table_schema", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"enabled"}, indexName = "idx_enable")
})
@TableName("sys_flance_saas_schema_table")
public class SchemaTableEntity extends BaseTable {

    @Column(notNull = true)
    private String tableId;

    @Column(notNull = true)
    private String schemaId;

    @Column(notNull = true)
    private Integer enabled;

}

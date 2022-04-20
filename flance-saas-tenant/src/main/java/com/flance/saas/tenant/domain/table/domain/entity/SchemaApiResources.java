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
@Table(tableName = "sys_flance_saas_schema_api", indexes = {
        @Index(columns = {"schema_id", "api_id"}, indexName = "un_schema_api", indexType = Index.IndexType.UNIQUE),
})
@TableName("sys_flance_saas_schema_api")
public class SchemaApiResources extends BaseTable {

    @Column(notNull = true)
    private String schemaId;

    @Column(notNull = true)
    private String apiId;

}

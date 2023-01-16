package com.flance.saas.tenant.domain.application.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_app_table", indexes = {
        @Index(columns = {"app_id", "table_id"}, indexName = "idx_app_table", indexType = Index.IndexType.UNIQUE)
})
@TableName("sys_flance_saas_app_table")
public class ApplicationTable extends BaseTable {

    @Column(notNull = true)
    private String appId;

    @Column(notNull = true)
    private String tableId;

}

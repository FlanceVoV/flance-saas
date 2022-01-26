package com.flance.saas.tenant.domain.table.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务表配置
 * 这些业务表都会在租户开户时生成，需要保证每个配置的都已经完成了对应的业务功能开发
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_table", indexes = {
        @Index(columns = {"table_name"}, indexName = "un_table_name", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"table_remark"}, indexName = "idx_table_remark")
})
@TableName("sys_flance_saas_tenant")
public class TableEntity extends BaseTable {

    /**
     * 表名 唯一索引 表的业务名，不带schema和后缀
     */
    private String tableName;

    /**
     * 表备注、说明
     */
    private String tableRemark;

    /**
     * 是否开放 1.开启 0.禁用
     */
    private Integer enabled;

}

package com.flance.saas.tenant.domain.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_business_id", indexes = {
        @Index(columns = {"business_id"}, indexName = "un_business_id", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"number_id"}, indexName = "un_number_id", indexType = Index.IndexType.UNIQUE)
})
@TableName("sys_flance_saas_business_id")
public class BusinessId extends BaseTable {

    @Column(notNull = true)
    private String businessId;

    @Column(notNull = true)
    private Integer numberId;

}

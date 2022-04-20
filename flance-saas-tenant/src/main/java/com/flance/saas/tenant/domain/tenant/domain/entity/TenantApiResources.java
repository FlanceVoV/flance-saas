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
@Table(tableName = "sys_flance_saas_tenant_api", indexes = {
        @Index(columns = {"tenant_id", "api_id"}, indexName = "un_tenant_api", indexType = Index.IndexType.UNIQUE),
})
@TableName("sys_flance_saas_tenant_api")
public class TenantApiResources extends BaseTable {

    @Column(notNull = true)
    private String tenantId;

    @Column(notNull = true)
    private String apiId;

}

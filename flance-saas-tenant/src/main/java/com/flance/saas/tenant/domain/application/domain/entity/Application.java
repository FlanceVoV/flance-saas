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
@Table(tableName = "sys_flance_saas_application", indexes = {
        @Index(columns = {"app_code"}, indexName = "un_app_code", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"app_name"}, indexName = "idx_app_name")
})
@TableName("sys_flance_saas_auth")
public class Application extends BaseTable {

    @Column(notNull = true, length = "10")
    private String appCode;

    @Column(notNull = true, length = "50")
    private String appName;

    @Column(notNull = true, length = "1000")
    private String appDesc;

    

}

package com.flance.saas.tenant.domain.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_auth", indexes = {
        @Index(columns = {"auth_code"}, indexName = "un_auth_code", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"api_id"}, indexName = "un_api_id", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"auth_name"}, indexName = "idx_auth_name")
})
@TableName("sys_flance_saas_auth")
public class AuthEntity extends BaseTable {

    @Column(notNull = true, length = "64")
    private String authCode;

    @Column(notNull = true, length = "64")
    private String  apiId;

    @Column(notNull = true, length = "64")
    private String tenantId;

    @Column(notNull = true)
    private String userType;

    @Column(notNull = true)
    private String authName;
}

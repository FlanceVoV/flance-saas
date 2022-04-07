package com.flance.saas.tenant.domain.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_user_role", indexes = {
        @Index(columns = {"user_id", "role_id", "tenant_id"}, indexName = "un_user_id", indexType = Index.IndexType.UNIQUE),
})
@TableName("sys_flance_saas_user_role")
public class UserRoleEntity extends BaseTable {

    @Column(length = "64")
    private String userId;

    @Column(length = "64")
    private String roleId;

    @Column(length = "64")
    private String userType;

    @Column(notNull = true)
    private String tenantId;

}

package com.flance.saas.tenant.domain.role.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_role_auth", indexes = {
        @Index(columns = {"auth_id", "role_id"}, indexName = "un_auth_role", indexType = Index.IndexType.UNIQUE),
})
@TableName("sys_flance_saas_role_auth")
public class RoleAuthEntity extends BaseTable {

    @Column(notNull = true, length = "64")
    private String authId;

    @Column(notNull = true, length = "64")
    private String roleId;

}

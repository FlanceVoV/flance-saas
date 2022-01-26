package com.flance.saas.tenant.domain.role.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_role_menu", indexes = {
        @Index(columns = {"role_id, menu_id"}, indexName = "un_role_menu_id", indexType = Index.IndexType.UNIQUE),
})
@TableName("sys_flance_saas_role_menu")
public class RoleMenuEntity extends BaseTable {

    private String roleId;

    private String menuId;

}

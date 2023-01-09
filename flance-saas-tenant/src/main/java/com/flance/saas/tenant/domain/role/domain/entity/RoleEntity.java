package com.flance.saas.tenant.domain.role.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 系统角色定义
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_role", indexes = {
        @Index(columns = {"role_code"}, indexName = "un_role_code", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"role_name"}, indexName = "idx_role_name")
})
@TableName("sys_flance_saas_role")
public class RoleEntity extends BaseTable {

    @Column(notNull = true)
    private String roleName;

    @Column(notNull = true)
    private String roleCode;

    /**
     * 允许访问的api id
     * ,隔开
     */
    @Column(length = "2000")
    private String authConfig;

    @Column(notNull = true)
    private Integer enabled;

    @Column(notNull = true)
    private String tenantId;

    @Column(notNull = true, length = "64")
    private String userType;

    @TableField(exist = false)
    private List<String> authConfigs;

    @TableField(exist = false)
    private List<AuthEntity> auths;

    @TableField(exist = false)
    private List<MenuEntity> menus;


}

package com.flance.saas.tenant.domain.menu.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_menu", indexes = {
        @Index(columns = {"menu_code"}, indexName = "un_menu_code", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"menu_name"}, indexName = "idx_menu_name"),
        @Index(columns = {"menu_route_path"}, indexName = "idx_menu_route_path"),
        @Index(columns = {"menu_type"}, indexName = "idx_menu_type"),
        @Index(columns = {"parent_id"}, indexName = "idx_parent_id"),
        @Index(columns = {"tenant_id"}, indexName = "idx_tenant_id"),
})
@TableName("sys_flance_saas_menu")
public class MenuEntity extends BaseTable {

    @Column(notNull = true)
    private String menuCode;

    @Column(notNull = true)
    private String menuName;

    @Column
    private String menuRoutePath;

    @Column(notNull = true)
    private Integer isButton;

    /**
     * 1.系统菜单
     * 2.默认提供的菜单
     * 3.租户自定义菜单（基于系统默认提供的菜单，进行重命名、重排）
     */
    @Column(notNull = true)
    private Integer menuType;

    @Column
    private String menuIcon;

    @Column(notNull = true)
    private String parentId;

    @Column(notNull = true)
    private Integer enabled;

    @Column(notNull = true)
    private String tenantId;

    @TableField(exist = false)
    private MenuEntity parentMenu;

}

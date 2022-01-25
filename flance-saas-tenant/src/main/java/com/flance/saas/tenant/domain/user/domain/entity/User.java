package com.flance.saas.tenant.domain.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_user", indexes = {
        @Index(columns = {"login_name"}, indexName = "un_login_name", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"user_name"}, indexName = "idx_user_name")
})
@TableName("sys_flance_saas_user")
public class User extends BaseTable {

    /**
     * 登录名
     */
    @Column(notNull = true)
    private String loginName;

    /**
     * 用户名
     */
    @Column(notNull = true)
    private String userName;

    /**
     * 密码
     */
    @Column(notNull = true)
    private String password;

    /**
     * 用户所有的租户（应用）
     */
    private List<Tenant> tenants;

}

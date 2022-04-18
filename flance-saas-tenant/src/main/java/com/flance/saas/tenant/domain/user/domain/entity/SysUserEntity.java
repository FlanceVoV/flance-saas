package com.flance.saas.tenant.domain.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.base.IUser;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 系统用户
 * 指：saas平台的系统管理后台运维账户
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(tableName = "sys_flance_saas_user", indexes = {
        @Index(columns = {"user_account"}, indexName = "un_user_account", indexType = Index.IndexType.UNIQUE),
        @Index(columns = {"user_name"}, indexName = "idx_user_name")
})
@TableName("sys_flance_saas_user")
public class SysUserEntity extends BaseTable implements IUser {

    /**
     * 登录名
     */
    @Column(notNull = true)
    private String userAccount;

    /**
     * 用户名
     */
    @Column(notNull = true)
    private String userName;

    /**
     * 用户手机号
     */
    @Column(notNull = true)
    private String userPhone;

    /**
     * 密码
     */
    @Column(notNull = true)
    private String userPassword;

    @TableField(exist = false)
    private List<MenuEntity> userMenus;

    @TableField(exist = false)
    private List<RoleEntity> userRoles;

    @TableField(exist = false)
    private List<AuthEntity> userAuths;


}

package com.flance.saas.tenant.domain.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantAppUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 系统表-外部用户
 * 指：实际的用户，一个用户可以被注册多个租户应用
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_flance_saas_app_user")
@Table(tableName = "sys_flance_saas_app_user")
public class AppUserEntity extends BaseTable {

    /**
     * 用户登录账户
     */
    @Column(notNull = true)
    private String userAccount;

    /**
     * 用户密码，密文
     */
    @Column(notNull = true)
    private String userPassword;

    /**
     * 绑定第三方登录
     */
    @Column
    private String openId;

    /**
     * 用户姓名
     */
    @Column(notNull = true)
    private String userName;

    /**
     * 用户昵称
     */
    @Column
    private String userNickName;

    /**
     * 用户头像
     */
    @Column
    private String userImages;

    /**
     * 注册身份证号
     */
    @Column(notNull = true)
    private String userIdNum;

    /**
     * 用户手机号
     */
    @Column(notNull = true)
    private String userMobile;

    /**
     * 是否启用（1.正常 0.禁用）
     */
    @Column(notNull = true)
    private Integer enabled;

    /**
     * 商户应用
     */
    @TableField(exist = false)
    private List<Tenant> appTenant;

    @TableField(exist = false)
    private List<MenuEntity> userMenus;

    @TableField(exist = false)
    private List<RoleEntity> userRoles;

}

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
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 系统表-商户用户
 * 指：在系统中注册租户应用的用户，一个商户用户可以有多个应用，应用直接的数据可以共享
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_flance_saas_merchant_user")
@Table(tableName = "sys_flance_saas_merchant_user", indexes = {
        @Index(indexName = "udx_user_account", columns = {"user_account"}, indexType = Index.IndexType.UNIQUE),
        @Index(indexName = "udx_open_id", columns = {"open_id"}, indexType = Index.IndexType.UNIQUE),
        @Index(indexName = "udx_user_id_num", columns = {"user_id_num"}, indexType = Index.IndexType.UNIQUE),
        @Index(indexName = "udx_user_mobile", columns = {"user_mobile"}, indexType = Index.IndexType.UNIQUE)
})
public class MerchantUserEntity extends BaseTable implements IUser {

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
     * 是否启用（1.正常 0.禁用 2.审核中）
     */
    @Column(notNull = true)
    private Integer enabled;

    /**
     * 最后登录ip
     */
    @Column(notNull = true)
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(notNull = true)
    private Date lastLoginTime;

    /**
     * 商户应用
     */
    @TableField(exist = false)
    private List<Tenant> merchantTenants;

    @TableField(exist = false)
    private List<MenuEntity> userMenus;

    @TableField(exist = false)
    private List<RoleEntity> userRoles;

    @TableField(exist = false)
    private List<AuthEntity> userAuths;
}

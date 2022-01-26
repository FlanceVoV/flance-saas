package com.flance.saas.tenant.domain.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.domain.entity.TenantMerchantUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 系统表-商户用户
 * 指：在系统中注册租户应用的用户，一个商户用户可以有多个应用，应用直接的数据可以共享
 * @author jhf
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_flance_saas_merchant_user")
public class MerchantUserEntity extends BaseTable {

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
    private String openId;

    /**
     * 用户姓名
     */
    @Column(notNull = true)
    private String userName;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户头像
     */
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
     * 商户应用
     */
    @TableField(exist = false)
    private List<TenantMerchantUser> merchantTenant;
}

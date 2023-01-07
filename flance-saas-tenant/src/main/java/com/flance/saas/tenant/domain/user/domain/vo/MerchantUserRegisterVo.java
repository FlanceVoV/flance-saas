package com.flance.saas.tenant.domain.user.domain.vo;

import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;


@Data
public class MerchantUserRegisterVo {

    /**
     * 用户登录账户
     */
    private String userAccount;

    /**
     * 用户密码，密文
     */
    private String userPassword;

    /**
     * 绑定第三方登录
     */
    private String openId;

    /**
     * 用户姓名
     */
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
    private String userIdNum;

    /**
     * 用户手机号
     */
    private String userMobile;

    /**
     * 是否启用（1.正常 0.禁用 2.审核中）
     */
    private Integer enabled;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    public MerchantUserEntity parseToEntity() {
        MerchantUserEntity entity = new MerchantUserEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

}

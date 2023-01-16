package com.flance.saas.tenant.domain.user.domain.vo;

import lombok.Data;

@Data
public class MerchantUserRegisterRsp {

    /**
     * 商户用户唯一编号
     */
    private String merchantId;

    /**
     * 用户登录账户
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userImages;

    /**
     * 绑定第三方登录
     */
    private String openId;


}

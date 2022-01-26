package com.flance.saas.tenant.infrastructure;

public interface SaasConstant {

    /**
     * saas 系统 登录 token key 前缀
     */
    String SAAS_SYS_LOGIN_TOKEN_KEY = "sys:saas:user:";

    /**
     * saas 系统 登录 token 过期时间 秒
     */
    Long SAAS_USER_EXP_TIME = 7200L;

}

package com.flance.saas.common.core;

public interface SaasConstant {

    String SYS_TOKEN_KEY = "sys:user:token:";

    String SYS_TENANT_KEY = "sys:tenant:";

    String SYS_TABLES_COMMON = "sys:tables:common:";

    /**
     * saas 系统 登录 token key 前缀
     */
    String SAAS_SYS_LOGIN_TOKEN_KEY = "sys:saas:sys-user:";

    /**
     * saas app 登录 token key 前缀
     */
    String SAAS_APP_LOGIN_TOKEN_KEY = "sys:saas:app-user:";

    /**
     * saas 商户 登录 token key 前缀
     */
    String SAAS_MERCHANT_LOGIN_TOKEN_KEY = "sys:saas:merchant-user:";

    /**
     * saas 系统 登录 token 过期时间 秒
     */
    Long SAAS_USER_EXP_TIME = 7200L;

    String DATA_STATUS_FIELD_NAME = "status";

    Integer DATA_STATUS_NORMAL = 1;

    Integer DATA_STATUS_DELETED = 0;

}

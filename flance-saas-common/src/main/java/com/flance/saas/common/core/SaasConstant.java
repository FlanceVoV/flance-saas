package com.flance.saas.common.core;

public interface SaasConstant {

    String SYS_TENANT_ID_SYSTEM = "system";

    String SYS_TENANT_KEY = "sys:tenant:";

    String SYS_TABLES_COMMON = "sys:tables:common:";

    /**
     * saas 系统 登录 token 过期时间 秒
     */
    Long SAAS_USER_EXP_TIME = 7200L;

    String DATA_STATUS_FIELD_NAME = "status";

    Integer DATA_STATUS_NORMAL = 1;

    Integer DATA_STATUS_DELETED = 0;

}

package com.flance.saas.common.utils;

import com.flance.saas.common.login.TenantChooseModel;

import static com.flance.saas.common.utils.ThreadLocalHelper.*;

public class TenantChooseUtil {

    public static void putTenantLogin(TenantChooseModel tenantChooseModel) {
        ThreadLocalHelper.put(TENANT_LOGIN_MODEL, tenantChooseModel);
    }

    public static TenantChooseModel getTenantLoginModel() {
        return ThreadLocalHelper.get(TENANT_LOGIN_MODEL);
    }

    public static void removeTenantLogin() {
        ThreadLocalHelper.remove(TENANT_LOGIN_MODEL);
    }



}

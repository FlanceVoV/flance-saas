package com.flance.saas.common.utils;

import com.flance.saas.common.login.TenantChooseModel;

import static com.flance.saas.common.utils.ThreadLocalHelper.*;

public class TenantChooseUtil {

    public static void putLogin(TenantChooseModel tenantChooseModel) {
        ThreadLocalHelper.put(LOGIN_MODEL, tenantChooseModel);
    }

    public static TenantChooseModel getLoginModel() {
        return ThreadLocalHelper.get(LOGIN_MODEL);
    }

    public static void removeLogin() {
        ThreadLocalHelper.remove(LOGIN_MODEL);
    }



}

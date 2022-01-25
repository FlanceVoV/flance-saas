package com.flance.saas.common.utils;

import com.flance.saas.common.login.BaseLogin;

import static com.flance.saas.common.utils.ThreadLocalHelper.*;

public class TokenUtil {

    public static void putLogin(BaseLogin baseLogin) {
        ThreadLocalHelper.put(LOGIN_MODEL, baseLogin);
    }

    public static BaseLogin getLoginModel() {
        return ThreadLocalHelper.get(LOGIN_MODEL);
    }

    public static void removeLogin() {
        ThreadLocalHelper.remove(LOGIN_MODEL);
    }



}

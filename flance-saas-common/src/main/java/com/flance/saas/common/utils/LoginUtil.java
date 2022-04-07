package com.flance.saas.common.utils;

import static com.flance.saas.common.utils.ThreadLocalHelper.LOGIN_MODEL;

public class LoginUtil {

    public static <T> void putLogin(T loginUser) {
        ThreadLocalHelper.put(LOGIN_MODEL, loginUser);
    }

    public static <T> T getLoginModel() {
        return ThreadLocalHelper.get(LOGIN_MODEL);
    }

    public static void removeLogin() {
        ThreadLocalHelper.remove(LOGIN_MODEL);
    }

}

package com.flance.saas.common.utils;

import com.flance.saas.common.core.ThreadLocalModel;
import com.flance.saas.common.login.BaseLogin;
import com.google.common.collect.Maps;

import java.util.Map;


public class ThreadLocalHelper {

    public static final String LOGIN_MODEL = "login-model";
    public static final String LOG_ID = "log-id";

    private final static Map<String, ThreadLocalModel> THREAD_LOCAL_MODEL_MAP = Maps.newConcurrentMap();

    static {
        THREAD_LOCAL_MODEL_MAP.put(LOGIN_MODEL, ThreadLocalModel.create());
        THREAD_LOCAL_MODEL_MAP.put(LOG_ID, ThreadLocalModel.create());
    }

    public static void put(String key, Object value) {
        THREAD_LOCAL_MODEL_MAP.get(key).put(value);
    }

    public static <T> T get(String key) {
        return (T) THREAD_LOCAL_MODEL_MAP.get(key).get();
    }

    public static void remove(String key) {
        THREAD_LOCAL_MODEL_MAP.get(key).remove();
    }

}

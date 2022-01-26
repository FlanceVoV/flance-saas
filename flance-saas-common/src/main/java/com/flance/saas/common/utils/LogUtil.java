package com.flance.saas.common.utils;

import com.flance.web.utils.RequestUtil;

import static com.flance.saas.common.utils.ThreadLocalHelper.LOG_ID;

public class LogUtil {

    public static void putLog(String logId) {
        ThreadLocalHelper.put(LOG_ID, logId);
        RequestUtil.setLogId(logId);
    }

    public static String getLogId() {
        return ThreadLocalHelper.get(LOG_ID);
    }

    public static void removeLogId() {
        RequestUtil.remove();
        ThreadLocalHelper.remove(LOG_ID);
    }

}

package com.flance.saas.common.utils;

import com.flance.web.utils.GsonUtils;
import com.flance.web.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static com.flance.saas.common.utils.ThreadLocalHelper.LOGIN_MODEL;

@Slf4j
public class LoginUtil {

    public static void loginSet(String key, RedisUtils redisUtils) {
        Set<String> keys = redisUtils.keys(key + ":*");
        // 如果已存在，则直接将之前的登录状态置为失效，重新登录（顶号）
        if (keys.size() > 0) {
            log.info("顶号登录，原token失效[{}]", GsonUtils.toJSONString(key));
            keys.forEach(redisUtils::clear);
        }
    }

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

package com.flance.saas.tenant.infrastructure;

import com.flance.web.utils.RedisUtils;

import java.util.Set;

public class LoginUtil {

    public static void loginSet(String key, RedisUtils redisUtils) {
        Set<String> keys = redisUtils.keys(key + ":*");
        // 如果已存在，则直接将之前的登录状态置为失效，重新登录（顶号）
        if (keys.size() > 0) {
            keys.forEach(redisUtils::clear);
        }
    }

}

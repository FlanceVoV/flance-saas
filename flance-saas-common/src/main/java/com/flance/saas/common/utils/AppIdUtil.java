package com.flance.saas.common.utils;

import cn.hutool.core.date.DateUtil;
import com.flance.saas.common.login.LoginInfo;

import java.util.Date;

public class AppIdUtil {

    public static String getRegisterMerchantUserId(String merchantIncNum) {
        return DateUtil.format(new Date(), "yyyyMMddHHmmss") + "_" + merchantIncNum;
    }

    public static String getRegisterMerchantId(Integer number) {
        LoginInfo loginInfo = LoginUtil.getLoginModel(LoginInfo.class);
        String merchantUserId = loginInfo.getUserId();
        return merchantUserId + "_" + number;
    }

}

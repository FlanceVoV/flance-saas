package com.flance.saas.tenant.infrastructure;

import com.flance.saas.common.core.AuthService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.common.utils.LoginUtil;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.web.utils.GsonUtils;
import com.flance.web.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    RedisUtils redisUtils;

    @Override
    public boolean checkTenantAuth(String tenantId) {
        String userInfo = LoginUtil.getLoginModel();
        if (StringUtils.isEmpty(userInfo)) {
            return false;
        }
        try {
            LoginUser user = GsonUtils.fromString(userInfo, LoginUser.class);
            List<String> tenants = user.getTenants();
            return tenants.contains(tenantId);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkUserAuth(String requestId) {
        boolean openFlag = redisUtils.hasKey(SaasConstant.SYS_OPEN_URL + requestId);
        if (openFlag) {
            return true;
        }
        String userInfo = LoginUtil.getLoginModel();
        if (StringUtils.isEmpty(userInfo)) {
            return false;
        }
        try {
            LoginUser user = GsonUtils.fromString(userInfo, LoginUser.class);
            List<String> auths = user.getAuths();
            return auths.contains(requestId);
        } catch (Exception e) {
            return false;
        }
    }
}

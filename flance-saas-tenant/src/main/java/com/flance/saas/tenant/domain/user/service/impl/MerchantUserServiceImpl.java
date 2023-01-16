package com.flance.saas.tenant.domain.user.service.impl;

import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.role.domain.entity.RoleEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.mapper.MerchantUserMapper;
import com.flance.saas.tenant.domain.user.service.BaseUserService;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import com.flance.saas.tenant.infrastructure.UnionIdCreator;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.flance.web.utils.RequestHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MerchantUserServiceImpl extends BaseService<String, MerchantUserMapper, MerchantUserEntity> implements MerchantUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private BaseUserService baseUserService;

    @Resource
    private UnionIdCreator unionIdCreator;

    @Override
    public MerchantUserEntity loginForOpenId(String openId) {
        return null;
    }

    @Override
    public MerchantUserEntity loginForPassword(String userAccount, String userPassword) {
        LambdaQueryWrapper<MerchantUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MerchantUserEntity::getUserAccount, userAccount);
        MerchantUserEntity found = this.getOne(lambdaQueryWrapper);
        AssertUtil.notNull(found, AssertException.getNormal("找不到用户，请确认账户是否输入正确[" + userAccount + "]", "-1"));
        String foundPassword = found.getUserPassword();
        boolean flag = passwordEncoder.matches(userAccount + userPassword, foundPassword);
        AssertUtil.mastTrue(flag, AssertException.getNormal("密码不正确，请确认后再尝试", "-1"));
        return found;
    }

    @Override
    public List<Tenant> getMerchantUserTenants(String userId) {
        return baseUserService.getTenantUserTenants(userId);
    }

    @Override
    public List<MenuEntity> getUserMenu(String userId, String tenantId) {
        return baseUserService.getUserMenu(userId, tenantId, SaasConstant.SYS_USER_TYPE_MERCHANT);
    }

    @Override
    public List<RoleEntity> getUserRole(String userId, String tenantId) {
        return baseUserService.getUserRole(userId, tenantId, SaasConstant.SYS_USER_TYPE_MERCHANT);
    }

    @Override
    public List<AuthEntity> getUserAuth(String userId, String tenantId) {
        return baseUserService.getUserAuth(userId, tenantId, SaasConstant.SYS_USER_TYPE_MERCHANT);
    }

    @Override
    public String encodePassword(String userAccount, String userPassword) {
        return passwordEncoder.encode(userAccount + userPassword);
    }

    @Override
    public String createIncId() {
        return unionIdCreator.creatMerchantUserId();
    }

    @Override
    public void setLastLoginIp(String userId) {
        String clientIP = ServletUtil.getClientIP(RequestHolder.getRequest());
        MerchantUserEntity waitUpdate = new MerchantUserEntity();
        waitUpdate.setId(userId);
        waitUpdate.setLastLoginIp(clientIP);
        waitUpdate.setLastLoginTime(new Date());
        this.updateById(waitUpdate);
    }
}

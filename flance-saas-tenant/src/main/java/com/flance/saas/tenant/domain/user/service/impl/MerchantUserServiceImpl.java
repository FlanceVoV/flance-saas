package com.flance.saas.tenant.domain.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import com.flance.saas.tenant.domain.role.service.RoleMenuService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.mapper.MerchantUserMapper;
import com.flance.saas.tenant.domain.user.service.BaseUserService;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import com.flance.saas.tenant.domain.user.service.UserRoleService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MerchantUserServiceImpl extends BaseService<String, MerchantUserMapper, MerchantUserEntity> implements MerchantUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private TenantService tenantService;

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
    public List<Tenant> getAppUserTenants(String appUserId) {
        return tenantService.getAppUserTenant(appUserId);
    }

    @Override
    public String encodePassword(String userAccount, String userPassword) {
        return passwordEncoder.encode(userAccount + userPassword);
    }

}

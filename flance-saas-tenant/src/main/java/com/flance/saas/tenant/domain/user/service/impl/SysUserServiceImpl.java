package com.flance.saas.tenant.domain.user.service.impl;

import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.domain.entity.SysUserEntity;
import com.flance.saas.tenant.domain.user.mapper.SysUserMapper;
import com.flance.saas.tenant.domain.user.service.BaseUserService;
import com.flance.saas.tenant.domain.user.service.SysUserService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.flance.web.utils.RequestHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysUserServiceImpl extends BaseService<String, SysUserMapper, SysUserEntity> implements SysUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private BaseUserService baseUserService;

    @Override
    public SysUserEntity login(String userAccount, String userPassword) {
        LambdaQueryWrapper<SysUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserEntity::getUserAccount, userAccount);
        SysUserEntity found = this.getOne(lambdaQueryWrapper);
        AssertUtil.notNull(found, AssertException.getNormal("找不到用户，请确认账户是否输入正确[" + userAccount + "]", "-1"));
        String foundPassword = found.getUserPassword();
        boolean flag = passwordEncoder.matches(userAccount + userPassword, foundPassword);
        AssertUtil.mastTrue(flag, AssertException.getNormal("密码不正确，请确认后再尝试", "-1"));
        return found;
    }

    @Override
    public void setUserMenu(SysUserEntity sysUserEntity) {
        baseUserService.setUserMenu(sysUserEntity, sysUserEntity.getId(), SaasConstant.SYS_TENANT_ID_SYSTEM, SaasConstant.SYS_USER_TYPE_SYS);
    }

    @Override
    public void setUserRole(SysUserEntity sysUserEntity) {
        baseUserService.setUserRole(sysUserEntity, sysUserEntity.getId(), SaasConstant.SYS_TENANT_ID_SYSTEM, SaasConstant.SYS_USER_TYPE_SYS);
    }

    @Override
    public void setUserAuth(SysUserEntity sysUserEntity) {
        baseUserService.setUserAuth(sysUserEntity, sysUserEntity.getId(), SaasConstant.SYS_TENANT_ID_SYSTEM, SaasConstant.SYS_USER_TYPE_SYS);
    }

    @Override
    public String encodePassword(String userAccount, String userPassword) {
        return passwordEncoder.encode(userAccount + userPassword);
    }

    @Override
    public void setLastLoginIp(String userId) {
        String clientIP = ServletUtil.getClientIP(RequestHolder.getRequest());
        SysUserEntity waitUpdate = new SysUserEntity();
        waitUpdate.setId(userId);
        waitUpdate.setLastLoginIp(clientIP);
        waitUpdate.setLastLoginTime(new Date());
        this.updateById(waitUpdate);
    }

}

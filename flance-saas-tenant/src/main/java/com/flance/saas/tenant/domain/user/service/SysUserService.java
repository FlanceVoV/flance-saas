package com.flance.saas.tenant.domain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.user.domain.entity.SysUserEntity;

public interface SysUserService extends IService<SysUserEntity> {

    SysUserEntity login(String userAccount, String userPassword);

    void setUserMenu(SysUserEntity sysUserEntity);

    void setUserRole(SysUserEntity sysUserEntity);

}

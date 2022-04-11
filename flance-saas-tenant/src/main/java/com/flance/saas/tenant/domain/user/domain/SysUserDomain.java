package com.flance.saas.tenant.domain.user.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.base.BaseDomain;
import com.flance.saas.tenant.domain.user.domain.entity.SysUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.saas.tenant.domain.user.service.SysUserService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * sysUser领域模型
 * @author jhf
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class SysUserDomain extends BaseDomain<String, SysUserEntity> {

    /**
     * 用户实体
     */
    @NonNull
    private SysUserEntity sysUserEntity;

    @NonNull
    private SysUserService sysUserService;

    public SysUserDomain(@NonNull SysUserEntity sysUserEntity,
                         @NonNull SysUserService sysUserService) {
        this.sysUserEntity = sysUserEntity;
        this.sysUserService = sysUserService;
        super.setInfo(sysUserEntity, sysUserService);
    }

    @Override
    public String createId() {
        return super.createSimpleUUID();
    }

    /**
     * 创建用户
     */
    public void register() {
        String password = sysUserService.encodePassword(sysUserEntity.getUserAccount(), sysUserEntity.getUserPassword());
        sysUserEntity.setUserPassword(password);
        super.create();
    }

    /**
     * 登录
     * @return  返回登录信息
     */
    public LoginUser login() {
        SysUserEntity logon = sysUserService.login(sysUserEntity.getUserAccount(), sysUserEntity.getUserPassword());
        sysUserService.setUserMenu(logon);
        sysUserService.setUserRole(logon);
        String token = IdUtil.fastSimpleUUID();
        return LoginUser.builder()
                .userId(logon.getId())
                .token(token)
                .userName(logon.getUserName())
                .userAccount(logon.getUserAccount())
                .userMenus(logon.getUserMenus())
                .userRoles(logon.getUserRoles())
                .userPhone(logon.getUserPhone())
                .build();
    }

    /**
     * 根据id查询
     * @return  返回
     */
    public SysUserEntity get() {
        SysUserEntity found = getById();
        sysUserService.setUserMenu(found);
        sysUserService.setUserRole(found);
        return found;
    }


}

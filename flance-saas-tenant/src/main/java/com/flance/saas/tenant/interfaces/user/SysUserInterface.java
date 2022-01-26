package com.flance.saas.tenant.interfaces.user;

import com.flance.saas.tenant.domain.user.domain.SysUserDomain;
import com.flance.saas.tenant.domain.user.domain.entity.SysUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUserSys;
import com.flance.saas.tenant.domain.user.service.SysUserService;
import com.flance.saas.tenant.infrastructure.SaasConstant;
import com.flance.web.utils.GsonUtils;
import com.flance.web.utils.RedisUtils;
import com.flance.web.utils.web.request.PageRequest;
import com.flance.web.utils.web.response.PageResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户服务
 * @author jhf
 */
@Service
public class SysUserInterface {

    @Resource
    SysUserService sysUserService;

    @Resource
    RedisUtils redisUtils;

    /**
     * 登录
     * @param userAccount   账户
     * @param userPassword  密码.md5
     * @return              返回用户信息
     */
    public LoginUserSys login(String userAccount, String userPassword) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserAccount(userAccount);
        sysUserEntity.setUserPassword(userPassword);
        SysUserDomain sysUserDomain = SysUserDomain.builder()
                .sysUserEntity(sysUserEntity)
                .sysUserService(sysUserService)
                .build();
        LoginUserSys loginUserSys = sysUserDomain.login();
        String userInfo = GsonUtils.toJSONString(loginUserSys);
        String key = SaasConstant.SAAS_SYS_LOGIN_TOKEN_KEY + loginUserSys.getToken();
        if (redisUtils.hasKey(key)) {
            redisUtils.setExp(key, SaasConstant.SAAS_USER_EXP_TIME);
            return loginUserSys;
        }
        redisUtils.add(key, userInfo, SaasConstant.SAAS_USER_EXP_TIME);
        return loginUserSys;
    }

    /**
     * 创建账户
     * @param sysUserEntity     用户实例
     */
    public void create(SysUserEntity sysUserEntity) {
        SysUserDomain sysUserDomain = SysUserDomain.builder()
                .sysUserService(sysUserService)
                .sysUserEntity(sysUserEntity)
                .build();
        sysUserDomain.create();
    }

    /**
     * 修改用户
     * @param sysUserEntity     用户实例
     */
    public void update(SysUserEntity sysUserEntity) {
        SysUserDomain sysUserDomain = SysUserDomain.builder()
                .sysUserService(sysUserService)
                .sysUserEntity(sysUserEntity)
                .build();
        sysUserDomain.create();
    }

    /**
     * 删除用户
     * @param id        用户主键
     */
    public void delete(String id) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(id);
        SysUserDomain sysUserDomain = SysUserDomain.builder()
                .sysUserService(sysUserService)
                .sysUserEntity(sysUserEntity)
                .build();
        sysUserDomain.create();
    }

    /**
     * 查询全部
     * @param sysUserEntity     用户实例
     * @return                  返回集合
     */
    public List<SysUserEntity> list(SysUserEntity sysUserEntity) {
        SysUserDomain sysUserDomain = SysUserDomain.builder()
                .sysUserService(sysUserService)
                .sysUserEntity(sysUserEntity)
                .build();
        return sysUserDomain.list();
    }

    /**
     * 分页查询
     * @param pageRequest       分页封装
     * @return                  返回分页对象
     */
    public PageResponse<SysUserEntity> page(PageRequest<SysUserEntity> pageRequest) {
        SysUserDomain sysUserDomain = SysUserDomain.builder()
                .sysUserService(sysUserService)
                .build();
        return sysUserDomain.page(pageRequest);
    }

    /**
     * 根据主键查询用户
     * @param id        用户主键
     * @return          返回单个对象
     */
    public SysUserEntity get(String id) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(id);
        SysUserDomain sysUserDomain = SysUserDomain.builder()
                .sysUserService(sysUserService)
                .sysUserEntity(sysUserEntity)
                .build();
        return sysUserDomain.get();
    }

}

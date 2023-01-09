package com.flance.saas.tenant.domain.user.domain;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.saas.tenant.domain.user.service.MerchantUserService;
import com.flance.web.utils.exception.BizException;
import com.flance.web.utils.exception.ParamException;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MerchantUserDomain {

    @NonNull
    private MerchantUserService merchantUserService;

    @NonNull
    private MerchantUserEntity merchantUserEntity;

    public LoginUser login() {
        MerchantUserEntity logon = merchantUserService.loginForPassword(merchantUserEntity.getUserAccount(), merchantUserEntity.getUserPassword());
        String token = IdUtil.fastSimpleUUID();
        merchantUserService.setLastLoginIp(logon.getId());
        return LoginUser.builder()
                .userAccount(logon.getUserAccount())
                .userTenants(merchantUserService.getMerchantUserTenants(logon.getId()))
                .token(token)
                .build();
    }

    public void beforeRegisterCheck() {
        if (null == merchantUserEntity.getUserMobile()) {
            throw new ParamException("用户手机号不允许为空", "101001");
        }
        if (null == merchantUserEntity.getUserIdNum()) {
            throw new ParamException("用户证件号不允许为空", "101002");
        }
        if (null == merchantUserEntity.getUserAccount()) {
            throw new ParamException("用户账号不允许为空", "101003");
        }
        if (null == merchantUserEntity.getUserPassword()) {
            throw new ParamException("用户密码不允许为空", "101004");
        }
        if (null == merchantUserEntity.getEnabled()) {
            throw new ParamException("是否启用不允许为空", "101005");
        }
        LambdaQueryWrapper<MerchantUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MerchantUserEntity::getUserAccount, merchantUserEntity.getUserAccount());
        MerchantUserEntity found = merchantUserService.getOne(queryWrapper);
        if (null != found) {
            throw new BizException("商户用户已存在，账号重复[" + merchantUserEntity.getUserAccount() + "]", "100001");
        }
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MerchantUserEntity::getUserIdNum, merchantUserEntity.getUserIdNum());
        found = merchantUserService.getOne(queryWrapper);
        if (null != found) {
            throw new BizException("商户用户已存在，身份证号重复[" + merchantUserEntity.getUserIdNum() + "]", "100002");
        }
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MerchantUserEntity::getUserMobile, merchantUserEntity.getUserMobile());
        found = merchantUserService.getOne(queryWrapper);
        if (null != found) {
            throw new BizException("商户用户已存在，手机号重复[" + merchantUserEntity.getUserMobile() + "]", "100003");
        }

        if (null != merchantUserEntity.getOpenId()) {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(MerchantUserEntity::getOpenId, merchantUserEntity.getOpenId());
            found = merchantUserService.getOne(queryWrapper);
            if (null != found) {
                throw new BizException("商户用户已存在，openId重复[" + merchantUserEntity.getUserMobile() + "]", "100003");
            }
        }
    }

    public void register() {
        beforeRegisterCheck();
        String encodePass = merchantUserService.encodePassword(merchantUserEntity.getUserAccount(), merchantUserEntity.getUserPassword());
        merchantUserEntity.setInsert();
        merchantUserEntity.setUserPassword(encodePass);
        merchantUserService.save(merchantUserEntity);
    }

}

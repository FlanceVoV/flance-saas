package com.flance.saas.tenant.domain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.user.domain.entity.MerchantUserEntity;

import java.util.List;

public interface MerchantUserService extends IService<MerchantUserEntity> {

    MerchantUserEntity loginForOpenId(String openId);

    MerchantUserEntity loginForPassword(String userAccount, String userPassword);

    List<Tenant> getAppUserTenants(String appUserId);

    String encodePassword(String userAccount, String userPassword);

}

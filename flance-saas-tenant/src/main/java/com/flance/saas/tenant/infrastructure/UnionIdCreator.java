package com.flance.saas.tenant.infrastructure;

import com.flance.saas.tenant.domain.unionid.service.UnionIdService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UnionIdCreator {

    @Resource
    private UnionIdService unionIdService;

    private final static String MERCHANT_KEY = "merchant";

    private final static String MERCHANT_USER_KEY = "merchant-user";

    private final static String TENANT_KEY = "tenant";

    public String creatMerchantId() {
        Integer incNum = unionIdService.createUnionIdByKey(MERCHANT_KEY);
        return incNum + "";
    }

    public String creatMerchantUserId() {
        Integer incNum = unionIdService.createUnionIdByKey(MERCHANT_USER_KEY);
        return incNum + "";
    }

    public String creatTenant() {
        Integer incNum = unionIdService.createUnionIdByKey(TENANT_KEY);
        return incNum + "";
    }

}

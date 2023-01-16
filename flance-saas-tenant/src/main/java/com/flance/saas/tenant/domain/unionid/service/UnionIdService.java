package com.flance.saas.tenant.domain.unionid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.unionid.domain.entity.UnionId;

public interface UnionIdService extends IService<UnionId> {

    Integer createUnionIdByKey(String bizKey);

}

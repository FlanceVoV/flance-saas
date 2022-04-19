package com.flance.saas.tenant.domain.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.tenant.domain.entity.BusinessId;

public interface BusinessIdService extends IService<BusinessId> {

    String getCurrentId(Integer numberId);

}

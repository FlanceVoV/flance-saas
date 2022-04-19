package com.flance.saas.tenant.domain.tenant.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.tenant.domain.entity.BusinessId;
import com.flance.saas.tenant.domain.tenant.mapper.BusinessIdMapper;
import com.flance.saas.tenant.domain.tenant.service.BusinessIdService;
import org.springframework.stereotype.Service;

@Service
public class BusinessIdServiceImpl extends BaseService<String, BusinessIdMapper, BusinessId> implements BusinessIdService {

    @Override
    public String getCurrentId(Integer numberId) {
        return this.baseMapper.getCurrentId(numberId);
    }
}

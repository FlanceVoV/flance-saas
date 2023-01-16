package com.flance.saas.tenant.domain.unionid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flance.saas.tenant.domain.unionid.domain.entity.UnionId;
import com.flance.saas.tenant.domain.unionid.mapper.UnionIdMapper;
import com.flance.saas.tenant.domain.unionid.service.UnionIdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnionIdServiceImpl extends ServiceImpl<UnionIdMapper, UnionId> implements UnionIdService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createUnionIdByKey(String bizKey) {
        baseMapper.incNumByBizKey(bizKey);
        return baseMapper.selectIncNumForUpdate(bizKey);
    }
}

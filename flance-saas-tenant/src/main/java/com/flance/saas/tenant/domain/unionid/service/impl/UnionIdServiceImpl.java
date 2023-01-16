package com.flance.saas.tenant.domain.unionid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flance.saas.tenant.domain.unionid.domain.entity.UnionId;
import com.flance.saas.tenant.domain.unionid.mapper.UnionIdMapper;
import com.flance.saas.tenant.domain.unionid.service.UnionIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UnionIdServiceImpl extends ServiceImpl<UnionIdMapper, UnionId> implements UnionIdService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createUnionIdByKey(String bizKey) {
        Integer number = baseMapper.selectIncNumForUpdate(bizKey);
        log.info("前 自增序列[{}][{}]", bizKey, number);
        baseMapper.incNumByBizKey(bizKey);
        number = baseMapper.selectIncNumForUpdate(bizKey);
        log.info("后 自增序列[{}][{}]", bizKey, number);
        return number;
    }
}

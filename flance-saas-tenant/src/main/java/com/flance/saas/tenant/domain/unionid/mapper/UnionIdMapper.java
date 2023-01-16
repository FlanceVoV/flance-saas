package com.flance.saas.tenant.domain.unionid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flance.saas.tenant.domain.unionid.domain.entity.UnionId;
import org.apache.ibatis.annotations.Param;

public interface UnionIdMapper extends BaseMapper<UnionId> {

    void incNumByBizKey(@Param("bizKey") String bizKey);

    Integer selectIncNumForUpdate(@Param("bizKey") String bizKey);

}

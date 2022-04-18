package com.flance.saas.tenant.domain.table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaTableEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemaTableMapper  extends BaseMapper<SchemaTableEntity> {
}

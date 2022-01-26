package com.flance.saas.tenant.domain.table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TableMapper extends BaseMapper<TableEntity> {
}

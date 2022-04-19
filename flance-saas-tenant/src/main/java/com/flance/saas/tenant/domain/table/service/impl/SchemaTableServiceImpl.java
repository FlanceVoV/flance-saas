package com.flance.saas.tenant.domain.table.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaTableEntity;
import com.flance.saas.tenant.domain.table.mapper.SchemaTableMapper;
import com.flance.saas.tenant.domain.table.service.SchemaTableService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemaTableServiceImpl extends BaseService<String, SchemaTableMapper, SchemaTableEntity> implements SchemaTableService {

    @Override
    public List<String> findTableIds(String schemaId) {
        LambdaQueryWrapper<SchemaTableEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SchemaTableEntity::getSchemaId, schemaId);
        List<SchemaTableEntity> finds = list(queryWrapper);
        List<String> result = Lists.newArrayList();
        if (null == finds || finds.size() == 0) {
            return result;
        }
        finds.forEach(item -> result.add(item.getTableId()));
        return result;
    }
}

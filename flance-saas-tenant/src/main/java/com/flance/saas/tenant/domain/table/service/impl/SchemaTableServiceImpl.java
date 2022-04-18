package com.flance.saas.tenant.domain.table.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaTableEntity;
import com.flance.saas.tenant.domain.table.mapper.SchemaTableMapper;
import com.flance.saas.tenant.domain.table.service.SchemaTableService;
import org.springframework.stereotype.Service;

@Service
public class SchemaTableServiceImpl extends BaseService<String, SchemaTableMapper, SchemaTableEntity> implements SchemaTableService {



}

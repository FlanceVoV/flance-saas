package com.flance.saas.tenant.domain.table.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaApiResources;
import com.flance.saas.tenant.domain.table.mapper.SchemaApiResourcesMapper;
import com.flance.saas.tenant.domain.table.service.SchemaApiResourcesService;
import org.springframework.stereotype.Service;

@Service
public class SchemaApiResourcesServiceImpl extends BaseService<String, SchemaApiResourcesMapper, SchemaApiResources> implements SchemaApiResourcesService {
}

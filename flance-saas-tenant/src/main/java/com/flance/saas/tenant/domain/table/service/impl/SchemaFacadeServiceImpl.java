package com.flance.saas.tenant.domain.table.service.impl;

import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.service.SchemaFacadeService;
import com.flance.saas.tenant.domain.table.service.SchemaService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchemaFacadeServiceImpl implements SchemaFacadeService {

    @Resource
    private SchemaService schemaService;

    @Override
    public SchemaEntity getSchemaById(String id) {
        return schemaService.getById(id);
    }

    @Override
    public SchemaEntity createInstance(SchemaEntity schemaEntity, Tenant tenant) {
        return schemaService.createInstance(schemaEntity, tenant);
    }
}

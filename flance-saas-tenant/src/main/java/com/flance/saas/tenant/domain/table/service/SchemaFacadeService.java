package com.flance.saas.tenant.domain.table.service;

import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;

public interface SchemaFacadeService {

    SchemaEntity getSchemaById(String id);

    SchemaEntity createInstance(SchemaEntity schemaEntity, Tenant tenant);

}

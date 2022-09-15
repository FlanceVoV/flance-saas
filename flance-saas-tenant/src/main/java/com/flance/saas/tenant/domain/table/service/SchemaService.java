package com.flance.saas.tenant.domain.table.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;

import java.util.List;

public interface SchemaService extends IService<SchemaEntity> {

    SchemaEntity createInstance(SchemaEntity schemaEntity, Tenant tenant);

}

package com.flance.saas.tenant.domain.table.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;

import java.util.List;

public interface SchemaService extends IService<SchemaEntity> {

    SchemaEntity create(SchemaEntity schemaEntity, String tenantId);

}

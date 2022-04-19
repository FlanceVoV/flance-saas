package com.flance.saas.tenant.domain.table.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaTableEntity;

import java.util.List;

public interface SchemaTableService extends IService<SchemaTableEntity> {

    List<String> findTableIds(String schemaId);

}

package com.flance.saas.tenant.domain.table.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;

import java.util.List;

public interface TableService extends IService<TableEntity> {


    void createTables(String schemaId, String schemaName, String suffix);
}

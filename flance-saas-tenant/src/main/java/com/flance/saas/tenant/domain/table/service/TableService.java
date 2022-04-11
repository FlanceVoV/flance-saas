package com.flance.saas.tenant.domain.table.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;

public interface TableService extends IService<TableEntity> {

    void createTable(TableEntity tableEntity, String schemaName, String suffix);

}

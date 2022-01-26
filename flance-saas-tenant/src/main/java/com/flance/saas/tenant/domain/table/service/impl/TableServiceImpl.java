package com.flance.saas.tenant.domain.table.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;
import com.flance.saas.tenant.domain.table.mapper.TableMapper;
import com.flance.saas.tenant.domain.table.service.TableService;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl extends BaseService<String, TableMapper, TableEntity> implements TableService {
}

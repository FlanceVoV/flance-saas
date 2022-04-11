package com.flance.saas.tenant.domain.table.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;
import com.flance.saas.tenant.domain.table.mapper.TableMapper;
import com.flance.saas.tenant.domain.table.service.TableService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TableServiceImpl extends BaseService<String, TableMapper, TableEntity> implements TableService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createTable(TableEntity tableEntity, String schemaName, String suffix) {
        try {
            Class<BaseTable> clazz = (Class<BaseTable>) Class.forName(tableEntity.getTableClassName());
            BaseTable baseTable = clazz.newInstance();
            baseTable.createTable(jdbcTemplate, schemaName, suffix);
        } catch (ClassNotFoundException | ClassCastException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            AssertUtil.throwError(AssertException.getNormal("表生成失败", "-1"));
        }
    }

}

package com.flance.saas.tenant.domain.table.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaTableEntity;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;
import com.flance.saas.tenant.domain.table.mapper.TableMapper;
import com.flance.saas.tenant.domain.table.service.SchemaTableService;
import com.flance.saas.tenant.domain.table.service.TableService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TableServiceImpl extends BaseService<String, TableMapper, TableEntity> implements TableService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private SchemaTableService schemaTableService;

    @Value("${flance.saas.common.autoAddColumns:false}")
    private Boolean autoAddColumns;

    @Override
    public void createTables(String schemaId, String schemaName, String suffix) {
        AssertUtil.notNull(schemaId, AssertException.getNormal("非法请求，schemaId为空", "-1"));
        LambdaQueryWrapper<SchemaTableEntity> schemaTableQuery = new LambdaQueryWrapper<>();
        schemaTableQuery.eq(SchemaTableEntity::getSchemaId, schemaId);
        List<String> tableIds = schemaTableService.findTableIds(schemaId);
        List<TableEntity> tables = listByIds(tableIds);
        tables.forEach(table -> createTable(table, schemaName, suffix));
    }

    private void createTable(TableEntity tableEntity, String schemaName, String suffix) {
        try {
            Class<BaseTable> clazz = (Class<BaseTable>) Class.forName(tableEntity.getTableClassName());
            BaseTable baseTable = clazz.newInstance();
            baseTable.createTable(jdbcTemplate, schemaName, suffix, autoAddColumns);
        } catch (ClassNotFoundException | ClassCastException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            AssertUtil.throwError(AssertException.getNormal("表生成失败", "-1"));
        }
    }



}

package com.flance.saas.tenant.domain.table.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.db.utils.SqlUtils;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.mapper.SchemaMapper;
import com.flance.saas.tenant.domain.table.service.SchemaService;
import com.flance.saas.tenant.domain.table.service.TableService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchemaServiceImpl extends BaseService<String, SchemaMapper, SchemaEntity> implements SchemaService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private TableService tableService;


    @Override
    public SchemaEntity createInstance(SchemaEntity schemaEntity, Tenant tenant) {
        AssertUtil.notNull(schemaEntity.getId(), AssertException.getNormal("database id is null", "-1"));
        AssertUtil.notNull(tenant, AssertException.getNormal("非法请求，tenant不存在", "-1"));

        SchemaEntity found = getById(schemaEntity.getId());
        AssertUtil.notNull(found, AssertException.getNormal("非法请求，database[" + schemaEntity.getId() + "]不存在", "-1"));
        if (SqlUtils.checkSchema(found.getSchemaName())) {
            String tenantSchemaName = found.getSchemaName() + "_" + tenant.getTenantId();
            jdbcTemplate.execute("create database if not exists " + tenantSchemaName + " default character set utf8mb4 collate utf8mb4_general_ci;");
            tableService.createTables(found.getId(), tenantSchemaName, tenant.getTenantSuffix());
        } else {
            throw new RuntimeException("can not create database [" + schemaEntity.getSchemaName() + "]");
        }
        return found;
    }
}

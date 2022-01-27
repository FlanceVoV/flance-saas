package com.flance.saas.tenant.domain.table.service.impl;

import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.db.utils.SqlUtils;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.mapper.SchemaMapper;
import com.flance.saas.tenant.domain.table.service.SchemaService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchemaServiceImpl extends BaseService<String, SchemaMapper, SchemaEntity> implements SchemaService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public SchemaEntity create(SchemaEntity schemaEntity) {
        if (SqlUtils.checkSchema(schemaEntity.getSchemaName())) {
            jdbcTemplate.execute("create database if not exists " + schemaEntity.getSchemaName() + " default character set utf8mb4 collate utf8mb4_general_ci;");
        } else {
            throw new RuntimeException("can not create database [" + schemaEntity.getSchemaName() + "]");
        }
        save(schemaEntity);
        return schemaEntity;
    }
}

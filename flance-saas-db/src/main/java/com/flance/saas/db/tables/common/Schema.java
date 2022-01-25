package com.flance.saas.db.tables.common;

import com.flance.saas.db.utils.SqlUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 表空间
 * @author jhf
 */
public class Schema {

    public void createSchema(JdbcTemplate jdbcTemplate, String schema) {
        if (SqlUtils.checkSchema(schema)) {
            jdbcTemplate.execute("create database if not exists " + schema + " default character set utf8mb4 collate utf8mb4_general_ci;");
        } else {
            throw new RuntimeException("can not create database [" + schema + "]");
        }
    }

}

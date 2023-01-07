package com.flance.saas.db.tables.common;

import com.flance.saas.db.utils.SqlUtils;
import com.flance.web.utils.exception.BizException;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 表空间
 * @author jhf
 */
@Data
@Deprecated
public class Schema {

    private String schemaName;

    private String schemaUniqueCode;

    public void createSchema(JdbcTemplate jdbcTemplate, String schema) {
        if (SqlUtils.checkSchema(schema)) {
            jdbcTemplate.execute("create database if not exists " + schema + " default character set utf8mb4 collate utf8mb4_general_ci;");
        } else {
            throw new BizException("can not create database [" + schema + "]", "-1");
        }
    }

}

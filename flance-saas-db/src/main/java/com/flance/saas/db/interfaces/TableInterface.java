package com.flance.saas.db.interfaces;

import com.flance.saas.db.init.InitTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TableInterface {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Value("${flance.saas.common.autoAddColumns:false}")
    private Boolean autoAddColumns;

    public void initSysTable() {
        InitTable.initSysTable(jdbcTemplate, "", autoAddColumns, "com.flance.saas");
    }

    public void initSelfTable(String ... scans) {
        InitTable.initSysTable(jdbcTemplate, "", autoAddColumns, scans);
    }

}

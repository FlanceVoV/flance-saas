package com.flance.saas.db.interfaces;

import com.flance.saas.db.init.InitTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TableInterface {

    @Resource
    JdbcTemplate jdbcTemplate;

    public void initSysTable() {
        InitTable.initSysTable(jdbcTemplate, "com.flance.saas");
    }

    public void initSelfTable(String ... scans) {
        InitTable.initSysTable(jdbcTemplate, scans);
    }

}

package com.flance.saas.db.init;

import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.db.utils.ClassUtils;
import com.flance.web.utils.GsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 初始化所有表
 * @author jhf
 */
@Slf4j
public class InitTable {

    public static void initSysTable(JdbcTemplate jdbcTemplate, Boolean autoAddColumns, String ... scans) {
        Set<Class<BaseTable>> classes = ClassUtils.getInstances(BaseTable.class, scans);
        classes.forEach(clazz -> {
            try {
                BaseTable baseTable = clazz.newInstance();
                baseTable.createTable(jdbcTemplate, "", "", autoAddColumns);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}

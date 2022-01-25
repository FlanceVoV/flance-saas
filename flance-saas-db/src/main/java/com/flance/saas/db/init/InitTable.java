package com.flance.saas.db.init;

import com.flance.saas.db.tables.common.BaseTable;
import com.flance.saas.db.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Set;

/**
 * 初始化所有表
 * @author jhf
 */
@Slf4j
public class InitTable {

    public static void initSysTable(JdbcTemplate jdbcTemplate, String ... scans) {
        Set<Class<BaseTable>> classes = ClassUtils.getInstances(BaseTable.class, scans);
        classes.forEach(clazz -> {
            try {
                BaseTable baseTable = clazz.newInstance();
                baseTable.createTable(jdbcTemplate, "", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}

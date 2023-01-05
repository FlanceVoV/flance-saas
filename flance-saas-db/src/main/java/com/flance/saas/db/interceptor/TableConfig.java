package com.flance.saas.db.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TableConfig {

    /**
     * 是否开启字段根据实例映射新增db字段，仅支持mysql
     */
    public static Boolean AUTO_ADD_COLUMNS;


    @Value("${flance.saas.common.autoAddColumns:false}")
    public void setAutoAddColumns(Boolean autoAddColumns) {
        TableConfig.AUTO_ADD_COLUMNS = autoAddColumns;
    }
}

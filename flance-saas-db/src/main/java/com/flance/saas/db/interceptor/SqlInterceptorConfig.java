package com.flance.saas.db.interceptor;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.flance.saas.common.login.BaseLogin;
import com.flance.saas.common.utils.LogUtil;
import com.flance.saas.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 切换schema，添加后缀
 * @author jhf
 */
@Slf4j
@Configuration
public class SqlInterceptorConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> {
            String logId = LogUtil.getLogId();
            BaseLogin baseLogin = TokenUtil.getLoginModel();
            if (null == baseLogin) {
                return tableName;
            }
            String tableSchema = baseLogin.getTenantSchema();
            String tableSuffix = baseLogin.getTenantSuffix();
            String editTableName = tableName;
            if (null != tableSchema && tableSchema.length() > 0) {
                editTableName = tableSchema + "." + editTableName;
            }
            if (null != tableSuffix && tableSuffix.length() > 0) {
                editTableName = editTableName + "_" + tableSuffix;
            }
            log.info("log-id[{}]，租户[{}]，切换数据库[{}]，表后缀[{}]", logId, baseLogin.getTenantId(), tableSchema, tableSuffix);
            return editTableName;
        });
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return interceptor;
    }

}

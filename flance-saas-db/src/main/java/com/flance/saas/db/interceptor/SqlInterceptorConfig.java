package com.flance.saas.db.interceptor;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.common.login.TenantChooseModel;
import com.flance.saas.common.utils.LogUtil;
import com.flance.saas.common.utils.TenantChooseUtil;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.flance.web.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 切换schema，添加后缀
 * @author jhf
 */
@Slf4j
@Configuration
public class SqlInterceptorConfig {

    @Resource
    RedisUtils redisUtils;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> {
            boolean isCommon = redisUtils.hasKey(SaasConstant.SYS_TABLES_COMMON + tableName);
            if (isCommon) {
                log.info("访问公共表[{}]", tableName);
                return tableName;
            }
            TenantChooseModel tenantChooseModel = TenantChooseUtil.getTenantLoginModel();
            AssertUtil.notNull(tenantChooseModel, AssertException.getNormal("系统异常，无法获取租户信息，[tenantChoose]不允许为空", "-1"));
            String tableSchema = tenantChooseModel.getTenantSchema();
            String tableSuffix = tenantChooseModel.getTenantSuffix();
            String editTableName = tableName;
            if (null != tableSchema && tableSchema.length() > 0) {
                editTableName = tableSchema + "." + editTableName;
            }
            if (null != tableSuffix && tableSuffix.length() > 0) {
                editTableName = editTableName + "_" + tableSuffix;
            }
            log.info("租户[{}]，切换数据库[{}]，表后缀[{}]", tenantChooseModel.getTenantId(), tableSchema, tableSuffix);
            return editTableName;
        });
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        return interceptor;
    }

}

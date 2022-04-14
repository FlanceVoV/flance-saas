package com.flance.saas.tenant.application;

import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.db.interfaces.TableInterface;
import com.flance.web.utils.RedisUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class Init {

    @Resource
    RedisUtils redisUtils;

    @Value("${flance.saas.common.tables}")
    List<String> commonTables;

    @Value("${flance.saas.common.init}")
    Boolean initSysTable;

    @Resource
    TableInterface tableInterface;

    @PostConstruct
    public void setCommonTable() {
        log.info("初始化table--{}", commonTables);
        commonTables.forEach(tableName -> redisUtils.add(SaasConstant.SYS_TABLES_COMMON + tableName, tableName));
        if (initSysTable) {
            tableInterface.initSysTable();
        }
    }

}

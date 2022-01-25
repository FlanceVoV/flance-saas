package com.flance.saas.api.init.handlers;

import com.flance.saas.db.interfaces.TableInterface;
import com.flance.saas.db.tables.ITable;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.user.domain.entity.User;
import com.flance.saas.tenant.interfaces.tenant.TenantInterface;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class TestHandler implements IHandler {

    @Resource
    TenantInterface tenantInterface;

    @Resource
    TableInterface tableInterface;

    @Override
    public void handler() {

        tableInterface.initSysTable();


        Tenant tenant = new Tenant();
        User user = new User();
        List<ITable> iTables = Lists.newArrayList(tenant, user);

        tenant.setId(tenant.createId());
        tenant.setTenantId("201");
        tenant.setTenantName("测试租户");
        tenant.setTenantSchema("flance_schema_test");
        tenant.setTenantSuffix("01");
        tenant.setDomain("/test1");
        tenant.setTables(iTables);
        tenant.setUserId("system_01");
        tenant.setPubKey("pubkey");
        tenant.setCreateDate(new Date());
        tenant.setCreateUserId("system_01");
        tenant.setStatus(0);
//        tenantInterface.testCreateNewTenant(tenant);

    }
}

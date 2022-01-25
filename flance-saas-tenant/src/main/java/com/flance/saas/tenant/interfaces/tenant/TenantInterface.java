package com.flance.saas.tenant.interfaces.tenant;

import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.ITable;
import com.flance.saas.db.tables.common.Schema;
import com.flance.saas.tenant.domain.tenant.domain.TenantDomain;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TenantInterface {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    TenantService tenantService;

    public void testCreateSchema(String schemaName) {
        Schema schema = new Schema();
        schema.createSchema(jdbcTemplate, schemaName);
    }

    public void testCreateNewTenant(Tenant tenant) {
        TenantDomain tenantDomain = TenantDomain.builder().tenant(tenant).tenantService(tenantService).build();
        List<ITable> list = tenant.getTables();
        tenantDomain.create();
        testCreateSchema(tenant.getTenantSchema());
        list.forEach(item -> item.createTable(jdbcTemplate, tenant.getTenantSchema(), tenant.getTenantSuffix()));

    }

    public List<Tenant> testList(Tenant tenant) {
        TenantDomain tenantDomain = TenantDomain.builder().tenant(tenant).tenantService(tenantService).build();
        return tenantDomain.list();
    }

}

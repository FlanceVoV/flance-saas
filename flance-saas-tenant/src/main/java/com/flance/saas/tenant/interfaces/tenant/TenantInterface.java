package com.flance.saas.tenant.interfaces.tenant;

import com.flance.saas.db.tables.ITable;
import com.flance.saas.db.tables.common.Schema;
import com.flance.saas.tenant.domain.tenant.domain.TenantDomain;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Component
public class TenantInterface {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    TenantService tenantService;

    /**
     * 创建表空间
     * @param schemaName    表空间名称
     * @return  返回表空间实例，可用于业务保存
     */
    public Schema createTenantSchema(String schemaName) {
        Schema schema = new Schema();
        schema.setSchemaName(schemaName);
        schema.setSchemaUniqueCode(UUID.randomUUID().toString());
        schema.createSchema(jdbcTemplate, schemaName);
        return schema;
    }

    public void createTenant(Tenant tenant) {
        TenantDomain tenantDomain = TenantDomain.builder().tenantService(tenantService).tenant(tenant).build();
        tenantDomain.create();
    }

    public Tenant getById(Tenant tenant) {
        TenantDomain tenantDomain = TenantDomain.builder().tenantService(tenantService).tenant(tenant).build();
        return tenantDomain.getById();
    }


}

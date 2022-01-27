package com.flance.saas.tenant.domain.table.domain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.saas.db.utils.SqlUtils;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.service.SchemaService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.flance.web.utils.SnowFlakeUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class SchemaDomain {

    @NonNull
    private SchemaService schemaService;

    @NonNull
    private SchemaEntity schemaEntity;

    public void createSchema() {
        AssertUtil.notNull(schemaEntity.getSchemaName(), AssertException.getNormal("参数名[表空间名称schemaName]不允许为空", "-1"));
        AssertUtil.notNull(schemaEntity.getSchemaUniqueCode(), AssertException.getNormal("参数名[表空间标识schemaUniqueCode]不允许为空", "-1"));
        schemaService.create(schemaEntity);
    }



}

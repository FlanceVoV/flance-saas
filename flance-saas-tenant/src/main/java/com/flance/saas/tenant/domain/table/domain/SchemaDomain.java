package com.flance.saas.tenant.domain.table.domain;

import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.service.SchemaService;
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



}

package com.flance.saas.tenant.domain.table.domain;

import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;
import com.flance.saas.tenant.domain.table.service.TableService;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TableDomain {

    @NonNull
    private TableEntity tableEntity;

    @NonNull
    private TableService tableService;

}

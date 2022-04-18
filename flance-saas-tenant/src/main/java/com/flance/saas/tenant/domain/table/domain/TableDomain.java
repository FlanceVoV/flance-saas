package com.flance.saas.tenant.domain.table.domain;

import cn.hutool.core.util.IdUtil;
import com.flance.saas.tenant.domain.base.BaseDomain;
import com.flance.saas.tenant.domain.table.domain.entity.TableEntity;
import com.flance.saas.tenant.domain.table.service.TableService;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TableDomain extends BaseDomain<String, TableEntity> {

    @NonNull
    private TableEntity tableEntity;

    @NonNull
    private TableService tableService;

    public TableDomain(@NonNull TableEntity tableEntity, @NonNull TableService tableService) {
        this.tableEntity = tableEntity;
        this.tableService = tableService;
        setInfo(tableEntity, tableService);
    }

    @Override
    public String createId() {
        return IdUtil.fastSimpleUUID();
    }
}

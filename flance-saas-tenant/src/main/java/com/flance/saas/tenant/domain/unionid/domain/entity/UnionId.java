package com.flance.saas.tenant.domain.unionid.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.common.BaseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_flance_union_id")
@Table(tableName = "sys_flance_union_id", indexes = {
        @Index(columns = {"biz_key"}, indexName = "idx_biz_key"),
        @Index(columns = {"biz_name"}, indexName = "idx_biz_name")
})
public class UnionId extends BaseTable {

    @Column(notNull = true)
    private String bizKey;

    @Column(notNull = true)
    private String bizName;

    @Column(notNull = true, defaultValue = "0")
    private Integer bizIncNum;

}

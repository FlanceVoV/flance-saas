package com.flance.saas.db.tables;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public interface ITable {

    /**
     * 主键
     * @return 返回分布式id
     */
    String getId();

    /**
     * 创建者
     * @return 返回创建者id
     */
    String getCreateUserId();

    /**
     * 返回创建时间
     * @return  返回创建日期
     */
    Date getCreateDate();

    /**
     * 最后者
     * @return 返回最后修改id
     */
    String getLastUpdateUserId();

    /**
     * 返回最后修改时间
     * @return  返回最后修改日期
     */
    Date getLastUpdateDate();

    /**
     * 返回数据状态
     * @return  返回数据状态
     */
    Integer getStatus();

    /**
     * 创建表
     */
    void createTable(JdbcTemplate jdbcTemplate, String schema, String suffix);

    /**
     * 新增字段
     */
    void addColumn(JdbcTemplate jdbcTemplate, String schema, String suffix);

}

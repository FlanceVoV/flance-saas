package com.flance.saas.db.tables.common;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.flance.jdbc.mybatis.common.IEntity;
import com.flance.saas.common.login.LoginInfo;
import com.flance.saas.common.utils.LoginUtil;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.ITable;
import com.flance.saas.db.utils.FieldUtils;
import com.flance.saas.db.utils.SqlUtils;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.flance.web.utils.GsonUtils;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.*;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Data
public abstract class BaseTable implements ITable, IEntity<String> {

    @TableId(type = IdType.INPUT)
    @Column(isPk = true, notNull = true)
    protected String id;

    @Column
    protected String createUserId;

    @Column
    protected Date createDate;

    @Column
    protected String lastUpdateUserId;

    @Column
    protected Date lastUpdateDate;

    @Column
    @TableLogic(value="1",delval="0")
    protected Integer status;

    @TableField(exist = false)
    protected List<String> ids;

    @TableField(exist = false)
    private String pkColumn;

    @Override
    public void createTable(JdbcTemplate jdbcTemplate, String schema, String suffix, Boolean autoAddColumns) {
        AssertUtil.mastTrue(SqlUtils.checkSchema(schema), AssertException.getNormal("schema名非法！[" + schema + "]", "-1"));
        String tableName = buildTable(schema, suffix);
        if (null == tableName) {
            return;
        }
        List<String> columnNames = getColumns();
        List<String> indexes = Lists.newArrayList();
        buildIndex(indexes);

        String sql = buildSql(tableName, columnNames, indexes);
        log.info("表创建[{}]", sql);
        jdbcTemplate.execute(sql);

        log.info("是否开启字段添加alert字段[{}]", autoAddColumns);
        if (autoAddColumns) {
            addColumn(jdbcTemplate, schema, suffix);
        }
    }

    @Override
    public void addColumn(JdbcTemplate jdbcTemplate, String schema, String suffix) {
        AssertUtil.mastTrue(SqlUtils.checkSchema(schema), AssertException.getNormal("schema名非法！[" + schema + "]", "-1"));
        String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name = ? and table_schema = ?;";

        Table table = this.getClass().getAnnotation(Table.class);
        if (null == table) {
            return;
        }
        String schemaName = StringUtils.hasLength(schema) ? schema : "flance_elearning";
        String tableName = StringUtils.hasLength(suffix) ? table.tableName() + "_" + suffix : table.tableName();
        List<String> hasColumns = doQuery(jdbcTemplate, sql, new String[]{tableName, schemaName}, (rs) -> {
            List<String> result = Lists.newArrayList();
            try {
                while (rs.next()) {
                    result.add(rs.getString("COLUMN_NAME"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }, Types.VARCHAR, Types.VARCHAR);
        List<String> config = getColumnNames();
        List<String> needAdd = Lists.newArrayList();
        config.forEach(con -> {
            if (!hasColumns.contains(con)) {
                needAdd.add(con);
            }
        });
        if (needAdd.size() > 0) {
            log.info("检测到新增字段 [{}]", GsonUtils.toJSONString(needAdd));
            log.info("开始新增字段 --------------------------- start");
            List<String> buildColumns = getColumns(needAdd.toArray(new String[]{}));
            buildColumns.forEach(alter -> {
                alter = alter.substring(0, alter.lastIndexOf(","));
                alter = " ALTER TABLE " + schemaName + "." + tableName + " ADD COLUMN " + alter + ";";
                log.info("执行alter sql [{}]", alter);
                jdbcTemplate.execute(alter);
            });
            log.info("结束新增字段 --------------------------- end");
        }
    }

    private List<String> getColumnNames() {
        List<String> columnNames = Lists.newArrayList();
        List<Field> fields = getFields();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (null == column) {
                continue;
            }
            String columnName = column.columnName();
            if ("".equals(columnName)) {
                columnName = FieldUtils.humpToLine(field.getName());
            }
            columnNames.add(columnName);
        }
        return columnNames;
    }

    /**
     * @param whiteList  列名白名单，如果有，则按照白名单配置，没有则全配置
     */
    private List<String> getColumns(String ... whiteList) {
        List<String> columnNames = Lists.newArrayList();
        List<Field> fields = getFields();
        for (Field field : fields) {
            String column = buildColumn(field, whiteList);
            if (null == column) {
                continue;
            }
            columnNames.add(column);
        }
        return columnNames;
    }

    private String buildTable(String schema, String suffix) {
        Table table = this.getClass().getAnnotation(Table.class);
        if (null == table) {
            log.error("找不到表注解[{}], 跳过表创建", this.getClass().getSimpleName());
            return null;
        }
        String tableName = table.tableName();
        if ("".equals(tableName)) {
            tableName = FieldUtils.humpToLine(this.getClass().getSimpleName());
        }
        if (null != schema && schema.length() > 0) {
            tableName = schema + "." + tableName;
        }
        if (null != suffix && suffix.length() > 0) {
            tableName = tableName + "_" + suffix;
        }
        return tableName;
    }

    private String buildColumn(Field field, String ... whiteList) {
        // field_name
        Column column = field.getAnnotation(Column.class);
        if (null == column) {
            log.info("字段没有列注释，跳过初始化[{}.{}]", this.getClass().getSimpleName(), field.getName());
            return null;
        }
        String columnName = column.columnName();
        if ("".equals(columnName)) {
            columnName = FieldUtils.humpToLine(field.getName());
        }

        if (whiteList.length > 0) {
            if (!Lists.newArrayList(whiteList).contains(columnName)) {
                return null;
            }
        }
        // length
        String length = column.length();

        // type
        String typeName = column.type().getTypeName();

        // default
        String defaultValue = column.defaultValue();

        // 自动映射默认属性
        if (column.type().equals(Column.ColumnType.AUTO)) {
            // 根据java字段类型获取mysql字段类型映射
            Column.ColumnType columnType = FieldUtils.getColumnType(field.getType());
            typeName = columnType.getTypeName();
            if (!StringUtils.hasLength(length) || length.equals("255")) {
                length = FieldUtils.getLength(field.getType());
            }
        }

        boolean isPk = column.isPk();
        if (isPk) {
            pkColumn = columnName;
        }

        if (column.notNull()) {
            String defaultV = " DEFAULT " + defaultValue ;
            if (defaultValue.equals("NULL")) {
                defaultV = "";
            }
            if (column.length().equals("-1")) {
                return " `" + columnName + "` " + typeName + " NOT NULL " + defaultV + ", ";
            }
            return " `" + columnName + "` " + typeName + "(" + length + ") NOT NULL " + defaultV + ", ";
        }
        if (column.length().equals("-1")) {
            return " `" + columnName + "` " + typeName + " DEFAULT " + defaultValue + ", ";
        }
        return " `" + columnName + "` " + typeName + " (" + length + ") DEFAULT " + defaultValue + ", ";
    }

    private void buildIndex(List<String> indexes) {
        // pk
        if (null != pkColumn) {
            indexes.add(" PRIMARY KEY (`" + pkColumn + "`) USING BTREE, ");
        }

        // index
        Table table = this.getClass().getAnnotation(Table.class);
        Index[] indexesConf = table.indexes();
        for (Index index : indexesConf) {
            String[] columns = index.columns();
            for (int i = 0; i < columns.length; i++) {
                columns[i] = "`" + columns[i] + "`";
            }
            String indexName = index.indexName();
            Index.IndexType type = index.indexType();
            String indexType = "";
            if (type.equals(Index.IndexType.UNIQUE)) {
                indexType = " UNIQUE ";
            }
            indexes.add(indexType + " KEY `" + indexName + "` (" + String.join(",", columns) + ") USING BTREE, ");
        }
    }

    private String buildSql(String tableName, List<String> columns, List<String> indexes) {
        if (indexes.size() == 0) {
            String last = columns.get(columns.size() - 1);
            last = " " + last.trim().substring(0, last.trim().length() - 1) + " ";
            columns.set(columns.size() - 1, last);
        } else {
            String last = indexes.get(indexes.size() - 1);
            last = " " + last.trim().substring(0, last.trim().length() - 1) + " ";
            indexes.set(indexes.size() - 1, last);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" create table if not exists ");
        sb.append(tableName);
        sb.append(" ( ");
        columns.forEach(sb::append);
        indexes.forEach(sb::append);
        sb.append(" ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;");

        return sb.toString();
    }

    private List<Field> getFields() {
        List<Field> fields = Lists.newArrayList();
        Class<?> tempClass = this.getClass();
        while (tempClass != null) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        return fields;
    }

    public String createId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    private <T> T doQuery(JdbcTemplate jdbcTemplate, String query, Object[] params, Function<ResultSet, T> function, int ... types) {
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(query, types);
        return jdbcTemplate.query(factory.newPreparedStatementCreator(params), function::apply);
    }

    public void setInsert(Snowflake snowflake) {
        LoginInfo loginInfo = LoginUtil.getLoginModel(LoginInfo.class);
        this.setId(snowflake.nextIdStr());
        this.setCreateUserId(loginInfo.getUserId());
        this.setCreateDate(new Date());
        this.setLastUpdateDate(new Date());
        this.setStatus(1);
    }

    public void setInsert() {
        LoginInfo loginInfo = LoginUtil.getLoginModel(LoginInfo.class);
        this.setId(IdUtil.fastSimpleUUID());
        this.setCreateUserId(loginInfo.getUserId());
        this.setCreateDate(new Date());
        this.setLastUpdateDate(new Date());
        this.setStatus(1);
    }

    public void setUpdate() {
        LoginInfo loginInfo = LoginUtil.getLoginModel(LoginInfo.class);
        this.setLastUpdateDate(new Date());
        this.setLastUpdateUserId(loginInfo.getUserId());
    }
}

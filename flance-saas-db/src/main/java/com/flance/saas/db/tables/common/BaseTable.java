package com.flance.saas.db.tables.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.flance.jdbc.mybatis.common.IEntity;
import com.flance.saas.db.annotation.Column;
import com.flance.saas.db.annotation.Index;
import com.flance.saas.db.annotation.Table;
import com.flance.saas.db.tables.ITable;
import com.flance.saas.db.utils.FieldUtils;
import com.flance.saas.db.utils.SqlUtils;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @TableLogic
    protected Integer status;

    @TableField(exist = false)
    protected List<String> ids;

    @TableField(exist = false)
    private String pkColumn;

    @Override
    public void createTable(JdbcTemplate jdbcTemplate, String schema, String suffix) {
        AssertUtil.mastTrue(SqlUtils.checkSchema(schema), AssertException.getNormal("schema名非法！[" + schema + "]", "-1"));
        String tableName = buildTable(schema, suffix);
        if (null == tableName) {
            return;
        }
        List<String> columnNames = Lists.newArrayList();
        List<Field> fields = getFields();
        for (Field field : fields) {
            String column = buildColumn(field);
            if (null == column) {
                continue;
            }
            columnNames.add(column);
        }
        List<String> indexes = Lists.newArrayList();
        buildIndex(indexes);

        String sql = buildSql(tableName, columnNames, indexes);
        log.info("表创建[{}]", sql);
        jdbcTemplate.execute(sql);

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

    private String buildColumn(Field field) {
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
            length = FieldUtils.getLength(field.getType());
        }

        boolean isPk = column.isPk();
        if (isPk) {
            pkColumn = columnName;
        }

        if (column.notNull()) {
            if (column.length().equals("-1")) {
                return " `" + columnName + "` " + typeName + " NOT NULL DEFAULT " + defaultValue + ", ";
            }
            return " `" + columnName + "` " + typeName + "(" + length + ") NOT NULL DEFAULT " + defaultValue + ", ";
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

}

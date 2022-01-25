package com.flance.saas.db.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
public @interface Column {

    String columnName() default "";

    ColumnType type() default ColumnType.AUTO;

    String length() default "255";

    boolean notNull() default false;

    boolean isPk() default false;

    enum ColumnType {
        VARCHAR(String.class, "varchar", "255"),
        INT(int.class, "int", "16"),
        DECIMAL(BigDecimal.class, "decimal", "16,2"),
        BIGINT(long.class, "bigint", "32"),
        DOUBLE(double.class, "double", "32"),
        DATETIME(Date.class, "datetime", "6"),
        TIMESTAMP(Timestamp.class, "timestamp", "14"),
        LONGTEXT(String.class, "longtext", "4000"),
        AUTO(null, null, null)
        ;

        private String typeName;
        private Class<?> javaType;

        ColumnType(Class<?> javaType, String typeName, String length) {
            this.javaType = javaType;
            this.typeName = typeName;
        }

        public String getTypeName() {
            return this.typeName;
        }

    }

}

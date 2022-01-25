package com.flance.saas.db.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
public @interface Index {

    IndexType indexType() default IndexType.NORMAL;

    String[] columns();

    String indexName();

    enum IndexType {

        UNIQUE("unique"),
        NORMAL("key");

        private String type;

        IndexType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}

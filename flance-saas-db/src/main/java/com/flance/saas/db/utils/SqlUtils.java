package com.flance.saas.db.utils;

import java.util.regex.Pattern;

public class SqlUtils {

    public static boolean checkSchema(String schema) {
        String regex = "^(?!\\d+$)[\\da-z_]+$";
        if (null == schema || schema.length() == 0) {
            return true;
        }
        if (schema.length() < 5|| schema.length() > 20) {
            return false;
        }
        return Pattern.matches(regex, schema);
    }

}

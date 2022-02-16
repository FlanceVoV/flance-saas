package com.flance.saas.db.utils;

import com.flance.saas.db.annotation.Column;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段工具类
 * @author jhf
 */
public class FieldUtils {

    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    private static final Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     **/
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 驼峰转下划线
     * */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static Column.ColumnType getColumnType(Class<?> fieldType) {
        if (Date.class.equals(fieldType)) {
            return Column.ColumnType.DATETIME;
        }
        if (String.class.equals(fieldType)) {
            return Column.ColumnType.VARCHAR;
        }
        if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
            return Column.ColumnType.INT;
        }
        if (BigDecimal.class.equals(fieldType)) {
            return Column.ColumnType.DECIMAL;
        }
        return Column.ColumnType.VARCHAR;
    }

    /**
     * 将字符串的首字母转大写
     */
    public static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    public static String getLength(Class<?> fieldType) {
        if (Date.class.equals(fieldType)) {
            return "6";
        }
        if (String.class.equals(fieldType)) {
            return "255";
        }
        if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
            return "16";
        }
        if (BigDecimal.class.equals(fieldType)) {
            return "10,2";
        }
        return "255";
    }

}

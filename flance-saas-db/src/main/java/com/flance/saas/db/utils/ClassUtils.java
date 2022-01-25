package com.flance.saas.db.utils;

import cn.hutool.core.util.ClassUtil;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassUtils {

    public static <T> T getInstances(Class<?> supClass, String ... scanPackages) {
        String aPackage = ClassUtil.getPackage(supClass);
        Set<Class<?>> classes = Sets.newHashSet();
        classes.addAll(ClassUtil.scanPackage(aPackage));
        if (null != scanPackages && scanPackages.length > 0) {
            Arrays.stream(scanPackages).forEach(packageName -> classes.addAll(ClassUtil.scanPackage(packageName)));
        }
        return (T) classes.stream().filter(sonClass -> {
            boolean allAssignableFrom = ClassUtil.isAllAssignableFrom(new Class[]{supClass}, new Class[]{sonClass});
            //要将 本身排除
            return allAssignableFrom && sonClass != supClass;
        }).collect(Collectors.toSet());
    }


}

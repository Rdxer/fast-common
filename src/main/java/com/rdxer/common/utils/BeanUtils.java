package com.rdxer.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class BeanUtils {
    /**
     * 获取空 属性名
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public static void copyNotNullProperties(Object source,Object target) {
        //支持部分更新
        String[] nullPropertyNames = BeanUtils.getNullPropertyNames(source);
        org.springframework.beans.BeanUtils.copyProperties(source,target, nullPropertyNames);
    }


}

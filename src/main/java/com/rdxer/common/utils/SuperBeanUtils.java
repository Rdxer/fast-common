package com.rdxer.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.PropertyAccessException;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuperBeanUtils {
    public static void copyNotNullProperties(Object source, Object target) {
        //支持部分更新
        String[] nullPropertyNames = BeanUtils.getNullPropertyNames(source);
        org.springframework.beans.BeanUtils.copyProperties(source, target, nullPropertyNames);
    }

    public static void copyMapValueToObject(Map<String, Object> source, Object target) {
        for (var kv : source.entrySet()) {
            modelSetValueWithPath(target, kv.getKey(), kv.getValue());
        }
    }

    /**
     * 获取空 属性名
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .filter(propertyName -> !"class".equals(propertyName))
                .toArray(String[]::new);
    }


    /**
     * 获取对象的 属性值
     */
    public static Object modelGetValue(Object source, String propertyName) {
        if (source == null) {
            return null;
        }
        if (source instanceof Map) {
            return ((Map<?, ?>) source).get(propertyName);
        }
        if (source instanceof List) {
            return ((List<?>) source).get(Integer.parseInt(propertyName));
        }

        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        Object propertyValue = wrappedSource.getPropertyValue(propertyName);

        return propertyValue;
    }

    /**
     * 获取对象的 属性值
     */
    public static Object modelGetValueWithPath(Object source, String paths) {
        if (source == null) {
            return null;
        }

        String[] pathList = paths.split("\\.");

        Object v = source;

        for (String path : pathList) {
            v = modelGetValue(v, path);
        }

        return v == source ? null : v;
    }


    /**
     * set对象的 属性值
     *
     * @return
     */
    public static boolean modelSetValue(Object target, String propertyName, Object newValue) {
        if (target == null) {
            return false;
        }
        if (target instanceof Map) {
            ((Map<String, Object>) target).put(propertyName, newValue);
            return true;
        }
        if (target instanceof List) {
            ((List) target).set(Integer.getInteger(propertyName), newValue);
            return true;
        }

        final BeanWrapper wrappedSource = new BeanWrapperImpl(target);

        try {
            wrappedSource.setPropertyValue(propertyName, newValue);
            return true;
        } catch (InvalidPropertyException e) {
            e.printStackTrace();
        } catch (PropertyAccessException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * set对象的 属性值
     */
    public static boolean modelSetValueWithPath(Object target, String paths, Object newValue) {
        if (target == null) {
            return false;
        }

        String[] ps = paths.split("\\.");

        List<String> pathList = Arrays.stream(ps).collect(Collectors.toList());

        String lastPath = pathList.remove(pathList.size() - 1);

        Object target_Temp = target;

        for (String path : pathList) {
            var target_Temp2 = modelGetValue(target_Temp, path);
            if (target_Temp2 == null) {
                Class<?> aClass = modelGetClass(target_Temp, path);
                Object model = modelMake(aClass);
                modelSetValue(target_Temp, path, model);
                target_Temp2 = model;
            }
            target_Temp = target_Temp2;
        }

        return modelSetValue(target_Temp, lastPath, newValue);
    }

    private static Object modelMake(Class<?> aClass) {
        try {
            Object o = aClass.getDeclaredConstructor().newInstance();
            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Class<?> modelGetClass(Object target_temp, String path) {
        Class<?> aClass = target_temp.getClass();
        Field field;
        try {
            field = aClass.getField(path);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
        Class<?> type = field.getType();
        return type;
    }

    /**
     * json 中转
     *
     * @param context
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToModel(Map<String, Object> context, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();

        String asString = null;
        try {
            asString = mapper.writeValueAsString(context);
            T o = mapper.readValue(asString, clazz);
            return o;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 模型转字典
     *
     * @param source
     * @return
     */
    public static Map<String, Object> modelToMap(Object source) {
        Map<String, Object> resp = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        try {
            String json = mapper.writeValueAsString(source);
            resp = mapper.readValue(json, typeFactory.constructMapType(HashMap.class, String.class, Object.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 模型转字典
     *
     * @param source
     * @return
     */
    public static Map<String, Object> modelToMapByBeanWrapper(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Map<String, Object> resp = new HashMap<>();
        for (PropertyDescriptor descriptor : wrappedSource.getPropertyDescriptors()) {
            String descriptorName = descriptor.getName();
            if (descriptorName.equals("class")) {
                continue;
            }
            resp.put(descriptorName, wrappedSource.getPropertyValue(descriptorName));
        }
        return resp;
    }
}

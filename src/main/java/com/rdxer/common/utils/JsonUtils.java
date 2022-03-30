package com.rdxer.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.List;

public class JsonUtils {
    /**
     * String all = ResourceLoaderUtils.loadResourceFileToString(resourceLoader,"classpath:config/menu.json");
     *
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> List<T> toObjectList(String json, Class<?> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<T> list = null;
        try {
            list = objectMapper.readValue(String.valueOf(json), typeFactory.constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public static String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return s;
    }
}

package com.rdxer.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ResourceLoaderUtils {
    /**
     * String all = ResourceLoaderUtils.loadResourceFileToString(resourceLoader,"classpath:config/menu.json");
     *
     * @param resourceLoader
     * @param location
     * @return
     * @throws IOException
     */
    public static <T> List<T> loadResourceFileToObjectList(ResourceLoader resourceLoader, String location, Class<T> clazz) throws IOException {
        String all = loadResourceFileToString(resourceLoader, location);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<T> list = objectMapper.readValue(String.valueOf(all), typeFactory.constructCollectionType(List.class, clazz));
        return list;

    }

    /**
     * String str = ResourceLoaderUtils.loadResourceFileToString(resourceLoader,"classpath:config/menu.json");
     *
     * @param resourceLoader
     * @param location
     * @return
     * @throws IOException
     */
    public static String loadResourceFileToString(ResourceLoader resourceLoader, String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        InputStream is = resource.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder all = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            all.append(line);
        }

        br.close();
        isr.close();
        is.close();
        return all.toString();
    }
}

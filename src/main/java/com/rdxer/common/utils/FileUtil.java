package com.rdxer.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileUtil {
    public static String readString(File file) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            StringBuilder content = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 写入文件
     */
    public static void writeString(File file, String content) {

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流释放资源
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 读取json类型的文件
     *
     * @param systemSetting
     * @param c
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> T readJson(File systemSetting, Class<T> c) {
        String content = readString(systemSetting);
        ObjectMapper objectMapper = new ObjectMapper();
        T t = objectMapper.readValue(content, c);
        return t;
    }

    @SneakyThrows
    public static <T> List<T> readJsonWithList(File systemSetting, Class<T> c) {
        String content = readString(systemSetting);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<T> t = objectMapper.readValue(content, typeFactory.constructCollectionType(List.class, c));
        return t;
    }

    /**
     * 写入json类型的文件
     *
     * @param systemSetting
     * @param o
     */
    @SneakyThrows
    public static void writeJson(File systemSetting, Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        writeString(systemSetting, jsonString);
    }
}

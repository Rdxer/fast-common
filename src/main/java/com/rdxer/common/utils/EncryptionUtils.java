package com.rdxer.common.utils;

import org.springframework.util.DigestUtils;

public class EncryptionUtils {
    public static String md5(String content) {
        String md5 = DigestUtils.md5DigestAsHex(content.getBytes());
        return md5;
    }

    public static Long md5ToLong(String content) {
        return md5ToLong(content, 8);
    }

    public static Long md5ToLong(String content, int len) {
        String md5 = md5(content);
        System.out.println(md5);
        String substring = md5.substring(0, len);
        long l = Long.parseLong(substring, 16);
        return l;
    }
}

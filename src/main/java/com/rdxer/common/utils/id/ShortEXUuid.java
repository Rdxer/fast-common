package com.rdxer.common.utils.id;

import java.util.UUID;

public class ShortEXUuid {

    public static char[] chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghijklmnopqrstuvwxy".toCharArray();


    public static String getShortUUID() {
        return getShortUUID(UUID.randomUUID(), 10);
    }

//    public static String getShortUUID(UUID uuidObj) {
//        StringBuffer shortBuffer = new StringBuffer();
//        String uuid = uuidObj.toString().replace("-", "");
//        int len = chars.length;
//
//        for (int i = 0; i < 8; i++) {
//            String str = uuid.substring(i * 4, i * 4 + 4);
//            int x = Integer.parseInt(str, 16);
//            shortBuffer.append(chars[x % len]);
//        }
//        return shortBuffer.toString();
//    }

    public static String getShortUUID(int len) {
        return getShortUUID(UUID.randomUUID(), len);
    }

    public static String getShortUUID(UUID uuidObj, int len) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = uuidObj.toString().replace("-", "");
        // 进制
        int ary = chars.length;
        int uuidLen = uuid.length();
        int count = uuidLen / len;
        int mod = uuidLen % len;
        if (mod > 0) {
            count += 1;
        }

        int remain = 0;
        for (int i = 0; i < len; i++) {
            int start = i * count;
            int end = i * count + count;
            if (end > uuidLen) {
                end = uuidLen;
            }
            if (start == end) {
                remain = len - i;
                break;
            }
            String str = uuid.substring(start, end);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % ary]);
        }

        if (remain > 0) {
            int ele = uuidLen / remain;
            for (int i = 0; i < remain; i++) {
                int start = i * ele;
                int end = start + ele;
                String str = uuid.substring(start, end);
                char[] chars = str.toCharArray();
                int sum = 0;
                for (char c : chars) {
                    sum += c;
                }
                shortBuffer.append(ShortEXUuid.chars[sum % ary]);
            }
        }

        return shortBuffer.toString();
    }
}
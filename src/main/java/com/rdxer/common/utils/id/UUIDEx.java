package com.rdxer.common.utils.id;

import com.rdxer.common.utils.Hashids;

import java.util.UUID;

public class UUIDEx {

    public static String makeShort() {
        return makeShort(10);
    }

    // 小于 7 容易重复， 建议 8以上
    // 8 在亿级生成 很容易碰撞
    // 10 建议长度
    // 16  在亿级 基本不碰撞
    public static String makeShort(int len) {
        return ShortEXUuid.getShortUUID(len);
    }

    public static String encodeSafeShort() {
        return ShortSafeUuid.shortUuid();
    }

    public static String encodeSafeShort(String uuid) {
        return ShortSafeUuid.shortUuid(uuid);
    }

    public static UUID decodeSafeShort(String uuid) {
        return ShortSafeUuid.getUuid(uuid);
    }

    public static String makeShortHash(Long id, String salt) {
        Hashids hashids = new Hashids(salt);
        String hash = hashids.encode(id);
        return hash;
    }

    public static long makeShortHash(String hashid, String salt) {
        Hashids hashids = new Hashids(salt);
        long[] hash = hashids.decode(hashid);
        return hash[0];
    }


}

package com.rdxer.common.utils;


import com.rdxer.common.utils.id.ShortEXUuid;
import com.rdxer.common.utils.id.ShortSafeUuid;
import com.rdxer.common.utils.id.UUIDEx;
import org.junit.jupiter.api.Test;

import java.util.*;

public class UUIDTest {
    @Test
    void testLen10X10() {
        for (int i = 0; i < 10; i++) {
            testLen10();
        }
    }

    @Test
    void testLen10() {
        Set set = new HashSet();
        long v = System.currentTimeMillis();
        System.out.println(v);
        for (int i = 0; i < 1000 * 10000; i++) {
            String s = UUIDEx.makeShort(10);
            if (set.contains(s)) {
                System.out.println(s);
                throw new RuntimeException();
            }
            set.add(s);
        }
        long v2 = System.currentTimeMillis() - v;
        System.out.println(v2);
    }

    @Test
    void testLen8() {
        Set set = new HashSet();
        long v = System.currentTimeMillis();
        System.out.println(v);
        for (int i = 0; i < 100 * 10000; i++) {
            String s = UUIDEx.makeShort(8);
            if (set.contains(s)) {
                System.out.println(s);
                throw new RuntimeException();
            }
            set.add(s);
        }
        long v2 = System.currentTimeMillis() - v;
        System.out.println(v2);
    }

    @Test
    void test() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        String s = UUIDEx.encodeSafeShort(uuid);
        System.out.println(s);
        String uuid1 = UUIDEx.decodeSafeShort(s).toString();
        System.out.println(uuid1);

    }

    @Test
    void test22212() {
        Set set = new HashSet();
        long v = System.currentTimeMillis();
        System.out.println(v);
        for (int i = 0; i < 100 * 10000; i++) {
            String s = ShortSafeUuid.shortUuid();
            if (set.contains(s)) {
                System.out.println(s);
                throw new RuntimeException();
            }
            set.add(s);
        }
        long v2 = System.currentTimeMillis() - v;
        System.out.println(v2);
    }

    @Test
    void test2getShortUUID() {
        Set set = new HashSet();

        long v = System.currentTimeMillis();
        System.out.println(v);
        Map<String, Long> map = new HashMap<>();
        Map<String, Long> map2 = new HashMap<>();
        for (long i = 0; i < 100 * 10000; i++) {
            String s = ShortEXUuid.getShortUUID();
//            System.out.println(s);
            if (set.contains(s)) {
                System.out.println(s);
                throw new RuntimeException();
            }
            int length = s.length();

            Long orDefault = map2.get(length + "");
            if (orDefault == null) {
                orDefault = Long.valueOf(0);
            }
            orDefault++;
            map2.put(length + "", orDefault);
            set.add(s);
            map.put(s, i);
        }
        long v2 = System.currentTimeMillis() - v;
        System.out.println(v2);

        for (Map.Entry<String, Long> entry : map2.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    @Test
    void test2getSafeShortUUID() {
        Set set = new HashSet();

        long v = System.currentTimeMillis();
        System.out.println(v);
        Map<String, Long> map = new HashMap<>();
        Map<String, Long> map2 = new HashMap<>();
        for (long i = 0; i < 100 * 10000; i++) {
            String s = ShortSafeUuid.shortUuid();

            if (set.contains(s)) {
                System.out.println(s);
                throw new RuntimeException();
            }
            int length = s.length();

            Long orDefault = map2.get(length + "");
            if (orDefault == null) {
                orDefault = Long.valueOf(0);
            }
            orDefault++;
            map2.put(length + "", orDefault);
            set.add(s);
            map.put(s, i);
        }
        long v2 = System.currentTimeMillis() - v;
        System.out.println(v2);

        for (Map.Entry<String, Long> entry : map2.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }


}

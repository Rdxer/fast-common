package com.rdxer.common.utils;


import com.rdxer.common.ex.ListEx;
import com.rdxer.common.utils.id.ShortEXUuid;
import com.rdxer.common.utils.id.ShortSafeUuid;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class AppTest {

    @Test
    void contextLoads() {

    }

    @Test
    void test1() {
        System.out.println("111");
    }

    @Test
    void test2() {
//        System.out.println(ShortUuid.Builder.alphabet.length);
    }

    @Test
    void test11() {
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        System.out.println(chars.length);
    }

    @Test
    void uuid1() {
        int v = 0x3E;
        System.out.println(v);
        System.out.println(ShortEXUuid.chars.length);
        for (int i = 0; i < 10000; i++) {
            String uuid = ShortEXUuid.getShortUUID();
//            System.out.println(uuid);
        }
    }

    @Test
    void uuid12() {
        char v = '0';
        String str = "";
        for (int i = 0; i < 10; i++) {
            String s = Character.valueOf((char) (v + i)).toString();
            str += s;
//            System.out.print(s);
        }
        System.out.println("");
        v = 'a';
        for (int i = 0; i <= 'z' - 'a'; i++) {
//            System.out.print(Character.valueOf((char) (v + i)).toString());
            String s = Character.valueOf((char) (v + i)).toString();
            str += s;
        }
        System.out.println("");
        v = 'A';
        for (int i = 0; i <= 'Z' - 'A'; i++) {
            String s = Character.valueOf((char) (v + i)).toString();
            str += s;
//            System.out.print(Character.valueOf((char) (v + i)).toString());
        }


        char[] chars = str.toCharArray();
        List<Integer> cs = new ArrayList<>();
        for (char c : chars) {
            cs.add((int) c);
        }

        System.out.println(cs);
        Collections.sort(cs);
        System.out.println(cs);

        List<Character> collect = cs.stream().map(v1 -> Character.valueOf((char) v1.intValue())).collect(Collectors.toList());
        String join = ListEx.join(collect, "");


        System.out.println(str);
        System.out.println(join);
        System.out.println(str.length());
        System.out.println(join.length());
        char[] alphabet = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"
                .toCharArray();
        System.out.println(alphabet.length);

    }


    @Test
    void test2222() {
        Set set = new HashSet();
        long v = System.currentTimeMillis();
        System.out.println(v);
        for (int i = 0; i < 100000; i++) {
            String s = ShortSafeUuid.shortUuid();
//            System.out.println(s);
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
    void test22212() {
        Set set = new HashSet();
        String s = ShortSafeUuid.shortUuid();
//            System.out.println(s);
        set.add(s);
        if (set.contains(s)) {
            System.out.println(s);
            throw new RuntimeException();
        }
        set.add(s);

    }
}

package com.rdxer.common.ex;

import java.util.List;

public class MathEx {

    /**
     *
     * [0,max)
     *
     * @param max
     * @return
     */
    public static int random(int max){
        return random(0,max);
    }
    /**
     *
     * [min,max)
     *
     * @param min
     * @param max
     * @return
     */
    public static int random(int min,int max){
        double random = Math.random();
        double v = random * (max - min);
        return (int)v + min;
    }

    /**
     *
     *
     * @param objects
     * @return
     */
    public static Object randomObject(Object... objects){
        int length = objects.length;
        int random = random(length);
        return objects[random];
    }

    public static <T> T randomList(List<T> list){
        int length = list.size();
        int random = random(length);
        return list.get(random);
    }
}

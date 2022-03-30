package com.rdxer.common.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapEx {
    public static void clearNullValue(Map<?, ?> map) {
        ArrayList<?> objects = new ArrayList<>(map.keySet());
        for (Object key : objects) {
            if (map.get(key) == null) {
                map.remove(key);
            }
        }
    }


    public static void forAll(Object object, MapForAllV forAllCallbacl) {
        forAllCallbacl.forAll(object);
        if (object instanceof List) {
            for (Object value : ((List) object).toArray()) {

                forAll(value, forAllCallbacl);
            }
        } else if (object instanceof Map) {
            for (Object value : ((Map) object).values()) {

                forAll(value, forAllCallbacl);
            }
        }
    }

    public static boolean isEmpty(Map<String, Object> map) {
        if (map == null){
            return true;
        }
        return map.isEmpty();
    }

    public interface MapForAll {
        void forAll(Object id, Object v);
    }

    public interface MapForAllV {
        void forAll(Object v);
    }
}

package com.rdxer.common.ex;

import com.rdxer.common.utils.SuperBeanUtils;

import java.util.Objects;

public class ObjectEx {

    public static <T> boolean isNull(O<T> returnCall) {
        return isNull(returnCall, null);
    }

    public static <T> boolean isNull(O<T> returnCall, E exCatchCallback) {
        try {
            T r = returnCall.r();
            if (r == null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            if (exCatchCallback != null) {
                exCatchCallback.e(e);
            }
        }
        return true;
    }

    public static <T> void fieldProces(Object o, String field, Trans<T> trans) {
        if (o == null){
            return;
        }
        Object value = SuperBeanUtils.modelGetValueWithPath(o, field);
        T r = trans.r((T) value);
        SuperBeanUtils.modelSetValueWithPath(o,field,r);
    }

    public static boolean equalsSafe(O<Boolean> o) {
        try {
            return o.r();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public interface O<T> {
        T r() throws Exception;
    }

    public interface R<T> {
        void r(T t);
    }
    public interface Trans<T> {
        T r(T t);
    }
    public interface MapTo<InT,OutT> {
        OutT r(InT t);
    }
    public interface E {
        void e(Exception e);
    }

    public static <T> T get(O<T> returnCall) {
        return get(returnCall, null);
    }

    public static <T> T getByReturn(O<T> returnCall,R<T> r) {
        T t = get(returnCall, null);
        if (t != null) {
            r.r(t);
        }
        return t;
    }

    public static <T> T get(O<T> returnCall, T defValue) {
        return get(returnCall, defValue, null);
    }

    public static <T> T get(O<T> returnCall, T defValue, E exCatchCallback) {

        try {
            T r = returnCall.r();
            if (r == null) {
                return defValue;
            }
            return r;
        } catch (Exception e) {
            if (exCatchCallback != null) {
                exCatchCallback.e(e);
            }
        }
        return defValue;
    }


    // 不为空的 equals
    public static boolean equals(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        return Objects.equals(obj, obj2);
    }
}

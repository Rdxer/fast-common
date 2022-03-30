package com.rdxer.common.ex;

import java.util.*;

public class ListEx {
    public interface MapTo<T, T2> {
        T2 to(T t);
    }

    public interface MapToList<T, T2> {
        List<T2> toList(T t);
    }

    public static <T, T2> List<T2> map(List<T> list, MapTo<T, T2> mapto) {
        List<T2> res = new ArrayList<>();
        for (T t : list) {
            T2 t2 = mapto.to(t);
            res.add(t2);
        }
        return res;
    }

    public static <T, T2> List<T2> flatMapToList(List<T> list, MapToList<T, T2> mapto) {
        List<T2> res = new ArrayList<>();
        for (T t : list) {
            List<T2> t2 = mapto.toList(t);
            res.addAll(t2);
        }
        return res;
    }

    /**
     * 获取第一个元素，没有则返回null
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getFirst(List<T> list) {
        if (isEmpty(list)) return null;
        return (T) list.get(0);
    }

    /**
     * 获取最后一个元素，没有则返回null
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getLast(List<T> list) {
        if (isEmpty(list)) return null;
        return (T) list.get(list.size() - 1);
    }

    /**
     * 判断是否为 null 或者  empty
     *
     * @param o   lamd 表达式，内部链式调用 null point excetion 报错 会捕获
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(ObjectEx.O<List<T>> o) {
        List<T> r1;
        try {
            r1 = o.r();
            return isEmpty(r1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否 为 null 或者  empty
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null) {
            return true;
        }
        if (list.size() == 0) {
            return true;
        }
        if (list.size() == 1 && list.get(0) == null) {
            return true;
        }
        return false;
    }

    /**
     * array 转 list
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(T[] values) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T value : values) {
            arrayList.add(value);
        }
        return arrayList;
    }

    /**
     * 随机获取一个元素
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> T randomElement(List<T> values) {
        if (isEmpty(values)) {
            return null;
        }
        int index = MathEx.random(values.size());
        T t = values.get(index);
        return t;
    }

    /**
     * 随机获取一个元素
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> T randomElement(T[] values) {
        if (values == null) {
            return null;
        }
        int index = MathEx.random(values.length);
        T t = values[index];
        return t;
    }

    /**
     * 两个 list 的元素的是否存在交集  Objects.equals(t, t1)
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> boolean intersectionExist(List<T> list1, List<T> list2) {

        if (ListEx.isEmpty(list1) || ListEx.isEmpty(list2)) {
            return false;
        }

        for (T t : list1) {
            for (T t1 : list2) {
                if (Objects.equals(t, t1)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取两个 list 的元素的交集  Objects.equals(t, t1)
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        ArrayList<T> list = new ArrayList<>();
        if (ListEx.isEmpty(list1) || ListEx.isEmpty(list2)) {
            return list;
        }

        for (T t : list1) {
            for (T t1 : list2) {
                if (Objects.equals(t, t1)) {
                    list.add(t);
                }
            }
        }

        return list;
    }

    /**
     * 扁平化 数组 (对象有 子节点)
     *
     * @param values
     * @param l
     * @param <T>
     * @return
     */
    public static <T> List<T> flatList(List<T> values, L<T> l) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>(values);

        for (T value : values) {
            List<T> flat = l.flat(value);
            List<T> list = flatList(flat, l);
            result.addAll(list);
        }

        return result;
    }


    public static <T> T getFirst(T[] types) {
        List<T> classes = toList(types);
        return getFirst(classes);
    }

    public static <T> List<T> getList(ObjectEx.O<List<T>> returnCall) {
        return getList(returnCall, null);
    }

    public static <T> List<T> getList(ObjectEx.O<List<T>> returnCall, List<T> defValue) {
        return ObjectEx.get(returnCall, defValue);
    }

    public static String join(List<?> objs, String separator) {
        if (isEmpty(objs)) {
            return "";
        }

        Object first = getFirst(objs);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(first.toString());

        for (int i = 1; i < objs.size(); i++) {
            stringBuilder.append(separator);
            stringBuilder.append(objs.get(i).toString());
        }

        return stringBuilder.toString();
    }


    public static <T> List<T> asList(T... objs) {
        return new ArrayList<T>(Arrays.asList(objs));
    }


    public interface L<T> {
        List<T> flat(T t);
    }


    public static <T> T safeGet(Object obj, int index) {
        T r = null;

        if (obj == null) {
            return null;
        }

        if (obj instanceof List) {
            if (((List<T>) obj).size() > index) {
                r = ((List<T>) obj).get(index);
            }
        }else if (obj.getClass().isArray()){
            T[] a = (T[]) obj;
            r = a[index];
        }

        return (T) r;
    }
}

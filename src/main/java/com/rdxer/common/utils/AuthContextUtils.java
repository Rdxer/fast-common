package com.rdxer.common.utils;


public class AuthContextUtils {

    //创建线程本地变量
    private static ThreadLocal<Object> auth = new ThreadLocal<Object>();

    public <T> T getAuth() {
        return (T) auth.get();
    }

    public <T> void setAuth(T auth) {
        AuthContextUtils.auth.set(auth);
    }

    public void clear(){
        AuthContextUtils.auth.remove();
    }
}

package com.origin.common;

//基于ThreadLocal封装工具类，用户保存和获取当前登录用户id

import java.util.Date;

public class BaseContext {
    //泛型存String，数据库中startuser和updateuser为varchar类型
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentuser(String username){
        threadLocal.set(username);
    }

    public static String getCurrentuser(){
        return threadLocal.get();
    }
}

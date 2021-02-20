package com.zb.redisdemo.controller;

/**
 * 双重检查(推荐使用)
 * volatile 防止指令重排序
 * synchronized 加锁
 */
public class Singleton{

    private volatile Singleton singleton;

    private Singleton(){}

    public Singleton getSingleton(){

        if (null != singleton){
            synchronized (Singleton.class){
                if (null != singleton){
                    singleton = new Singleton();
                }
            }
        }
    return  singleton;
    }
}
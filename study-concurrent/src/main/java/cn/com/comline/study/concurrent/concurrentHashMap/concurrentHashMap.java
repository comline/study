package cn.com.comline.study.concurrent.concurrentHashMap;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class concurrentHashMap {
    static ConcurrentHashMap chm = new ConcurrentHashMap();

    public static void main(String[] args) {
        chm.put("", "");
        //Collections.synchronizedMap()//通过这个方法可以将不安全线程修改成安全的线程
        System.out.println("Hello World!");
    }
}

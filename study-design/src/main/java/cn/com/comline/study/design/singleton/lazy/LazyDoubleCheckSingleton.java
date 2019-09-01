package cn.com.comline.study.design.singleton.lazy;

import sun.plugin2.gluegen.runtime.CPU;

public class LazyDoubleCheckSingleton {
    private static LazyDoubleCheckSingleton lazy = null;

    private LazyDoubleCheckSingleton() {

    }

    //适中的方案
    //双重检查锁
    public static LazyDoubleCheckSingleton getInstance() {
        if (lazy == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazy == null) {
                    lazy = new LazyDoubleCheckSingleton();
                    //CPU执行的时候会转换为JVM执行执行
                    //1、分配内存给这个对象，volatile
                    //2、初始化对象
                    //3、将初始化好的对象和内存地址建立关联，赋值
                    //4、用户初次访问
                }
            }
        }
        return lazy;
    }
}

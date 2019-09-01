package cn.com.comline.study.design.singleton.lazy;

//性能最优的一种写法
//理解内部类的执行逻辑
public class LazyInnerClassSingleton {

    //虽然构造方法自由了，但是逃不过反射的法眼
    private LazyInnerClassSingleton() {
        if (LazyHolder.LAZY != null) {
            throw new RuntimeException("不允许构建多个实例");
        }
    }

    //懒汉式单例
    //LazyHolder里面的逻辑需要等到外部方法调用才执行
    //巧妙利用了内部类的特性
    //JVM底层执行逻辑，完美地避免了线程安全问题
    public static final LazyInnerClassSingleton getInstance() {
        return LazyHolder.LAZY;

    }

    private static class LazyHolder {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}

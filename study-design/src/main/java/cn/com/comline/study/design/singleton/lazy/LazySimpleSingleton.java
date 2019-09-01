package cn.com.comline.study.design.singleton.lazy;

public class LazySimpleSingleton {
    private static LazySimpleSingleton lazy = null;

    private LazySimpleSingleton() {

    }

    //JDK1.6对synchronized性能优化不少
    //不可避免地还是存在一定的性能问题
    public static LazySimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }
}

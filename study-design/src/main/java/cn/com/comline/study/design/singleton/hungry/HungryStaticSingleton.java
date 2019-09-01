package cn.com.comline.study.design.singleton.hungry;

public class HungryStaticSingleton {
    public static final HungryStaticSingleton hungrySingleton;

    static {
        hungrySingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {

    }

    public static HungryStaticSingleton getInstance() {
        return hungrySingleton;
    }
}

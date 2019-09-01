package cn.com.comline.study.design.singleton.hungry;

public class HungrySingleton {
    public static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}

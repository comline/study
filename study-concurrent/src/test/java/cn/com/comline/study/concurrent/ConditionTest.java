package cn.com.comline.study.concurrent;

import cn.com.comline.study.concurrent.condition.ConditionNotify;
import cn.com.comline.study.concurrent.condition.ConditionWait;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    @Test
    public void condition(){
        Lock lock = new ReentrantLock();//重入锁
        Condition condition = lock.newCondition();
        new Thread(new ConditionNotify(lock,condition)).start();
        new Thread(new ConditionWait(lock,condition)).start();
    }
}

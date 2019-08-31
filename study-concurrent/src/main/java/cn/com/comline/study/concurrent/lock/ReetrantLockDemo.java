package cn.com.comline.study.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockDemo {
    static Lock lock = new ReentrantLock();
    //synchronized的原子操作改造成Lock

    public static void main(String[] args) {
        lock.lock();//获得锁
        //read;
        lock.unlock();//释放锁
    }
}

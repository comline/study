package cn.com.comline.study.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo extends Thread {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            new CountDownLatchDemo().start();
        }
        countDownLatch.countDown();
        countDownLatch.countDown();
    }
    @Override
    public void run() {
        try{
            countDownLatch.await();//阻塞 1000个线程
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("ThreadName：" + Thread.currentThread().getName());
    }
}

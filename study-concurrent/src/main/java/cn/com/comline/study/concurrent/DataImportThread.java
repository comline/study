package cn.com.comline.study.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DataImportThread extends Thread {
    private CyclicBarrier cyclicBarrier;
    private String path;
    public DataImportThread(CyclicBarrier cyclicBarrier,String path){
        this.cyclicBarrier = cyclicBarrier;
        this.path = path;
    }
    public void run(){
        System.out.println("开始导入：" + path +"数据");
        try {
            cyclicBarrier.await();//阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

package cn.com.comline.study.design.factory.abstractf;

public class JavaVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制Java视频");
    }
}

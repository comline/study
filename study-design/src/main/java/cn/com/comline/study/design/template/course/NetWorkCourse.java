package cn.com.comline.study.design.template.course;

public abstract class NetWorkCourse {
    protected final void createCourse(){
        //1、发布预习资料

        //2、制作PPT课件
        this.createPPT();
        //3、在线直播
        this.liveVideo();
        //4、提交课件、课堂笔记
        this.postNote();
        //5、提交源码
        this.postSource();
        //6、布置作业，有些课是没有作业，有些课是有作业的
        //如果有作业，检查作业，如果没有作业，完成
        if(needHomeWork()){
            checkHomeWork();
        }
    }

    abstract void checkHomeWork();

    protected boolean needHomeWork() {
        return false;
    }

    final void postSource() {
        System.out.println("提交源代码");
    }

    final void postNote() {
        System.out.println("提交作业和笔记");
    }

    final void liveVideo() {
        System.out.println("录制视频");
    }

    final void createPPT() {
        System.out.println("制作PPT");
    }
}

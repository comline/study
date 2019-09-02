package cn.com.comline.study.design.template.course;

public class BigDataCourse extends NetWorkCourse {

    private boolean needHomeWorkFlag = false;

    public BigDataCourse(boolean needHomeWorkFlag){
        this.needHomeWorkFlag = needHomeWorkFlag;
    }
    @Override
    void checkHomeWork() {
        System.out.println("检查导数据的课后作业");
    }

    @Override
    protected boolean needHomeWork() {
        return this.needHomeWorkFlag;
    }
}

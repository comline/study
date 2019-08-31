package cn.com.comline.study.design.factory.abstractf;

public class JavaNote implements INote {
    @Override
    public void edit() {
        System.out.println("编写Java笔记");
    }
}

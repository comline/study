package cn.com.comline.study.design.factory.abstractf;

import cn.com.comline.study.design.factory.ICourse;

public class JavaCourseFactory implements ICourseFactory{
    @Override
    public INote createNote() {
        return new JavaNote();
    }

    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }
}

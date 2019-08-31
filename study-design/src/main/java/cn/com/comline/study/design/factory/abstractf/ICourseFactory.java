package cn.com.comline.study.design.factory.abstractf;

import cn.com.comline.study.design.factory.ICourse;

public interface ICourseFactory {

    INote createNote();

    IVideo createVideo();
}

package cn.com.comline.study;

import cn.com.comline.study.design.factory.abstractf.JavaCourseFactory;
import org.junit.Test;

public class AbstractFactroyTest {
    @Test
    public void test(){
        JavaCourseFactory factory = new JavaCourseFactory();
        factory.createNote().edit();
        factory.createVideo().record();
    }
}

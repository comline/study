package cn.com.comline.study.spring;

import cn.com.comline.study.spring.framework.context.CApplicationContext;
import org.junit.Test;

public class CSpringTest {
    @Test
    public void springBiTest(){
        CApplicationContext context = new CApplicationContext("classpath:application.properties");
        System.out.println(context);
    }
}

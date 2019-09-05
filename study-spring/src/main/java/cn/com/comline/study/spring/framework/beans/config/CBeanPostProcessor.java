package cn.com.comline.study.spring.framework.beans.config;

public class CBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean,String beanName)throws Exception{
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean,String beanName)throws Exception{
        return bean;
    }
}

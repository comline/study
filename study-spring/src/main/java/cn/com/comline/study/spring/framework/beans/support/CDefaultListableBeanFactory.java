package cn.com.comline.study.spring.framework.beans.support;

import cn.com.comline.study.spring.framework.beans.config.CBeanDefinition;
import cn.com.comline.study.spring.framework.context.support.CAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CDefaultListableBeanFactory extends CAbstractApplicationContext {

    //存储注册信息的BeanDefinition
    protected final Map<String,CBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}

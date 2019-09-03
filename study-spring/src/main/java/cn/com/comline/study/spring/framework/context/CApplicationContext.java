package cn.com.comline.study.spring.framework.context;

import cn.com.comline.study.spring.framework.beans.CBeanFactory;
import cn.com.comline.study.spring.framework.beans.CBeanWrapper;
import cn.com.comline.study.spring.framework.beans.config.CBeanDefinition;
import cn.com.comline.study.spring.framework.beans.support.CBeanDefinitionReader;
import cn.com.comline.study.spring.framework.beans.support.CDefaultListableBeanFactory;

import java.util.List;
import java.util.Map;

/**
 * 按之前源码分析的套路，IOC DI MVC AOP
 */
public class CApplicationContext extends CDefaultListableBeanFactory implements CBeanFactory {

    private String[] configLocations;

    private CBeanDefinitionReader reader;

    public CApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
    }

    public void reflesh() {
        //1、定位 定位配置文件
        reader = new CBeanDefinitionReader(this.configLocations);

        //2、加载配置文件，扫描相关的那类，把它们封装成BeanDefinition
        List<CBeanDefinition> cBeanDefinitions = reader.loadBeanDefinitions();

        //3、注册，把配置信息放到容器里面（伪IOC容器）
        doRegisterBeanDefinition(cBeanDefinitions);

        //4、把不是延时加载的类，有提前初始化
        doAutowired();
    }

    //只处理非延时加载的情况
    private void doAutowired() {
        for (Map.Entry<String,CBeanDefinition> beanDefinitionEntry: super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().isLazyInit()){
                getBean(beanName);
            }
        }
    }

    private void doRegisterBeanDefinition(List<CBeanDefinition> cBeanDefinitions) {
        for (CBeanDefinition beanDefinition : cBeanDefinitions) {

            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    public Object getBean(String beanName) {
        //1、初始化
        instantiateBean(beanName, new CBeanDefinition());

        //循环依赖
        //先有鸡还是先有蛋的问题，一个方法搞不定，要分两次

        //2、注入
        populateBean(beanName,new CBeanDefinition(),new CBeanWrapper());

        return null;
    }

    private void populateBean(String beanName, CBeanDefinition cBeanDefinition, CBeanWrapper cBeanWrapper) {
    }

    private void instantiateBean(String beanName, CBeanDefinition cBeanDefinition) {
    }
}

package cn.com.comline.study.spring.framework.context;

import cn.com.comline.study.spring.framework.annotation.CAutowired;
import cn.com.comline.study.spring.framework.annotation.CController;
import cn.com.comline.study.spring.framework.annotation.CService;
import cn.com.comline.study.spring.framework.beans.config.CBeanPostProcessor;
import cn.com.comline.study.spring.framework.core.CBeanFactory;
import cn.com.comline.study.spring.framework.beans.CBeanWrapper;
import cn.com.comline.study.spring.framework.beans.config.CBeanDefinition;
import cn.com.comline.study.spring.framework.beans.support.CBeanDefinitionReader;
import cn.com.comline.study.spring.framework.beans.support.CDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 按之前源码分析的套路，IOC DI MVC AOP
 */
public class CApplicationContext extends CDefaultListableBeanFactory implements CBeanFactory {

    private String[] configLocations;

    private CBeanDefinitionReader reader;

    //单例IOC容器缓存
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>();

    //通用的IOC容器
    private Map<String, CBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, CBeanWrapper>();

    public CApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            reflesh();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        for (Map.Entry<String, CBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegisterBeanDefinition(List<CBeanDefinition> cBeanDefinitions) {
        for (CBeanDefinition beanDefinition : cBeanDefinitions) {

            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    public Object getBean(String beanName) throws Exception {
        CBeanDefinition cBeanDefinition = this.beanDefinitionMap.get(beanName);
//        cBeanDefinition.setFactoryBeanName(beanName);

        Object instance = null;

        //这个逻辑还不严谨，自己可以去参考spring源码
        //工厂模式 + 策略模式
        CBeanPostProcessor postProcessor = new CBeanPostProcessor();

        postProcessor.postProcessBeforeInitialization(instance, beanName);

        instance = instantiateBean(beanName, cBeanDefinition);
        //3、把这个对象封装到BeanWrapper中
        CBeanWrapper beanWrapper = new CBeanWrapper(instance);
        //factoryBeanInstanceCache


        //4、把BeanWrapper存储IOC容器里面


        //1、初始化

        //循环依赖
        //先有鸡还是先有蛋的问题，一个方法搞不定，要分两次

        //2、拿到BeanWrapper之后，把BeanWrapper保存到IOC容器中去
//        if(factoryBeanInstanceCache.containsKey(beanName)){
//            throw new Exception("The" + beanName + "is exists!!");
//        }
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        postProcessor.postProcessAfterInitialization(instance, beanName);
        //3、注入
        populateBean(beanName, new CBeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrapperdInstance();
    }

    private void populateBean(String beanName, CBeanDefinition cBeanDefinition, CBeanWrapper cBeanWrapper) {
        Object instance = cBeanWrapper.getWrapperdInstance();

//        cBeanDefinition.getBeanClassName()
        Class<?> clazz = cBeanWrapper.getWrapperClass();
        //判断只有加了注解的类，才执行依赖注入
        if (!(clazz.isAnnotationPresent(CController.class) || clazz.isAnnotationPresent(CService.class))) {
            return;
        }

        //获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CAutowired.class)) {
                continue;
            }
            CAutowired autowired = field.getAnnotation(CAutowired.class);
            String autoWiredBeanName = autowired.value().trim();
            if ("".equals(autoWiredBeanName)) {
                autoWiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                //为什么会是null
                if (this.factoryBeanInstanceCache.get(autoWiredBeanName) == null) {
                    continue;
                }

//                if(instance == null){
//                    continue;
//                }
                field.set(instance, this.factoryBeanInstanceCache.get(autoWiredBeanName).getWrapperdInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private Object instantiateBean(String beanName, CBeanDefinition cBeanDefinition) {
        //1、拿到要实例化的对象的类名
        String className = cBeanDefinition.getBeanClassName();
        //2、反射实例化，得到一个对象
        Object instance = null;
        try {
            //假设默认就是单例,细节暂不考虑，先把主线拉通
            if (this.singletonObjects.containsKey(className)) {
                instance = this.singletonObjects.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.singletonObjects.put(className, instance);
                this.singletonObjects.put(cBeanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }
}

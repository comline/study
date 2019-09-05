package cn.com.comline.study.spring.framework.core;

/**
 * 单例工厂的顶层设计
 */
public interface CBeanFactory {

    /**
     * 根据beanName从IOC容器中获取一个实例Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws Exception;


    Object getBean(Class<?> beanClass) throws Exception;
}

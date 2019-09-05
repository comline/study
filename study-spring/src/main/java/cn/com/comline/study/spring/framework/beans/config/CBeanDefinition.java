package cn.com.comline.study.spring.framework.beans.config;

import lombok.Data;

@Data
public class CBeanDefinition {
    private String beanClassName;

    private boolean lazyInit = false;

    private String factoryBeanName;

    private boolean isSingleton = true;
}

package cn.com.comline.study.spring.framework.beans.support;

import cn.com.comline.study.spring.framework.beans.config.CBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CBeanDefinitionReader {

    private List<String> registyBeanClasses = new ArrayList<String>();
    private Properties config = new Properties();

    //固定配置文件中的key，相当于xml的规范
    private final String SCAN_PACKAGE = "scanPackage";

    public CBeanDefinitionReader(String... locations) {
        //通过URL定位找到其锁对应的文件，然后转换为文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        //转换为文件路径，实际上就是把.替换为/就OK了
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File calssDir = new File(url.getFile());
        for (File file : calssDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                registyBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    //把配置文件中扫描到的所有配置信息转换为CBeanDefinition对象，以便于之后IOC操作方便
    public List<CBeanDefinition> loadBeanDefinitions() {
        List<CBeanDefinition> result = new ArrayList<CBeanDefinition>();
        for (String className : registyBeanClasses) {
            CBeanDefinition beanDefinition = doCreateBeanDefinition(className);
            if(null == beanDefinition){
                continue;
            }
            result.add(beanDefinition);
        }
        return result;
    }

    //把每一个配置信息解析成BeanDefinitioin
    private CBeanDefinition doCreateBeanDefinition(String className) {
        try {
            Class<?> beanClass = Class.forName(className);
            //有可能是一个接口，用它的实现类作为beanClassName
            if (beanClass.isInterface()) {
                return null;
            }
            CBeanDefinition beanDefinition = new CBeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(beanClass.getSimpleName());
            return beanDefinition;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}

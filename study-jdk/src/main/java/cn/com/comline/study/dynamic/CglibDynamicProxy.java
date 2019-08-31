package cn.com.comline.study.dynamic;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxy implements MethodInterceptor {
    private Object target;

    public Object getInstance(Object object){
        this.target = target;
        Enhancer enhancer = new Enhancer();//加强器
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("记录日志开始");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("记录日志结束");
        return result;
    }
}

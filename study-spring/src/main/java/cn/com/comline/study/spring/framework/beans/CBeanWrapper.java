package cn.com.comline.study.spring.framework.beans;

public class CBeanWrapper {

    private Object wrappedInstance;

    private Class<?> wrappedClass;

    public CBeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrapperdInstance() {
        return null;
    }

    // 返回代理以后的Class
    // 可能会是这个 $Proxy0
    public Class<?> getWrapperClass() {
        return null;
    }
}

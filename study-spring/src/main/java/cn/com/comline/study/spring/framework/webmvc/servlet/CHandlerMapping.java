package cn.com.comline.study.spring.framework.webmvc.servlet;

import javafx.beans.binding.ObjectExpression;
import lombok.Data;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

@Data
public class CHandlerMapping {
    private Object controller;//保存方法对应的实例
    private Method method;//保存映射的方法
    private Pattern pattern;//URL的正则匹配

    public CHandlerMapping(Pattern pattern, Object controller, Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }
}

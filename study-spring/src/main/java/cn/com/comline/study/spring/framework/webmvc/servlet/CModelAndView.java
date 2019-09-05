package cn.com.comline.study.spring.framework.webmvc.servlet;

import lombok.Data;

import java.util.Map;

@Data
public class CModelAndView {

    private String viewName;

    private Map<String,?> model;

    public CModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public CModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }
}

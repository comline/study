package cn.com.comline.study.spring.framework.webmvc.servlet;

import java.io.File;
import java.util.Locale;

public class CViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFX = ".html";

    private File templateRootDir;

//    private String viewName;

    public CViewResolver(String templateRoot) {
        String templaterRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        templateRootDir = new File(templaterRootPath);
    }

    public CView resolveViewName(String viewName, Locale locale) throws Exception {
        if (null == viewName || "".equals(viewName.trim())) {
            return null;
        }
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFX);
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", ""));
        return new CView(templateFile);
    }
}

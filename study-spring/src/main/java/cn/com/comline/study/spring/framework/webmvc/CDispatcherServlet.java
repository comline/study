package cn.com.comline.study.spring.framework.webmvc;

import cn.com.comline.study.spring.framework.annotation.CController;
import cn.com.comline.study.spring.framework.annotation.CRequestMapping;
import cn.com.comline.study.spring.framework.context.CApplicationContext;
import cn.com.comline.study.spring.framework.webmvc.servlet.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CDispatcherServlet extends HttpServlet {
    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private CApplicationContext context;

    private List<CHandlerMapping> handlerMappings = new ArrayList<>();

    private Map<CHandlerMapping, CHandlerAdapter> handlerAdapters = new HashMap<CHandlerMapping, CHandlerAdapter>();

    public List<CViewResolver> viewResolvers = new ArrayList<CViewResolver>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1、通过从request中拿到URL，去配置一个HandlerMapping
        CHandlerMapping handler = getHandler(req);

        if (handler == null) {
            processDispatchResult(req,resp,new CModelAndView("404"));
            return;
        }

        //2、准备调用前的参数
        CHandlerAdapter ha = getHandlerAdapter(handler);

        //3、真正的调用方法,返回ModelAndView存储了要穿页面上值，和页面模板的名称
        CModelAndView mv = ha.handle(req, resp, handler);

        //这一步才是真正的输出
        processDispatchResult(req, resp, mv);
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, CModelAndView mv) throws Exception {
        //把给我的ModleAndView变成一个HTML、OuputStream、json、freemark、veolcity
        //ContextType
        if (null == mv) {
            return;
        }

        //如果ModelAndView不为null，怎么办？
        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (CViewResolver viewResolver : this.viewResolvers) {
            CView view = viewResolver.resolveViewName(mv.getViewName(), null);
            view.render(mv.getModel(), req, resp);
            return;
        }
    }

    private CHandlerAdapter getHandlerAdapter(CHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        CHandlerAdapter ha = this.handlerAdapters.get(handler);
        if (ha.supports(handler)) {
            return ha;
        }
        return null;
    }


    private CHandlerMapping getHandler(HttpServletRequest req) throws Exception {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (CHandlerMapping handler : this.handlerMappings) {
            try {
                Matcher matcher = handler.getPattern().matcher(url);
                //如果没有匹配上继续下一个匹配
                if (!matcher.matches()) {
                    continue;
                }

                return handler;
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、初始化ApplicationContext
        context = new CApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        //2、初始化Spring 九大组件
        initStrategies(context);
    }


    //初始化策略
    protected void initStrategies(CApplicationContext context) {
        //多文件上传的组件
        initMultipartResolver(context);

        //初始化本地语言环境
        initLocaleResolver(context);

        //初始化模板处理器
        initThemeResolver(context);

        //handlerMapping，必须实现
        initHandlerMappings(context);

        //初始化参数适配器，必须实现
        initHandlerAdapters(context);

        //初始化异常拦截器
        initHandlerExceptionResolvers(context);

        //初始化视图预处理器
        initRequestToViewNameTranslator(context);

        //初始化视图转换器，必须实现
        initViewResolvers(context);

        //参数缓存器
        initFlashMapManager(context);
    }

    private void initFlashMapManager(CApplicationContext context) {
    }

    private void initViewResolvers(CApplicationContext context) {
        //拿到模板的存放目录
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new CViewResolver(templateRoot));
        }
    }

    private void initRequestToViewNameTranslator(CApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(CApplicationContext context) {
    }

    private void initHandlerAdapters(CApplicationContext context) {
        //把一个requet请求编程一个handler，参数都是字符串的，自动配到handler中的形参
        //可想而知，他要拿到HandleMapping才能干活
        //意味着有一个HandlerMapping就有一个HandlerAdapter
        for (CHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new CHandlerAdapter());
        }
    }

    private void initHandlerMappings(CApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {
                Object controller = context.getBean(beanName);
                Class<?> clazz = controller.getClass();
                if (!clazz.isAnnotationPresent(CController.class)) {
                    continue;
                }

                String baseUrl = "";
                //获取Controller的url配置
                if (clazz.isAnnotationPresent(CRequestMapping.class)) {
                    CRequestMapping requestMapping = clazz.getAnnotation(CRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                //获取Method的url配置
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {

                    //没有加RequestMapping注解的直接忽略
                    if (!method.isAnnotationPresent(CRequestMapping.class)) {
                        continue;
                    }

                    //映射URL
                    CRequestMapping requestMapping = method.getAnnotation(CRequestMapping.class);
                    //  /demo/query

                    //  (//demo//query)

                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);

                    this.handlerMappings.add(new CHandlerMapping(pattern, controller, method));
                    System.out.println("Mapped " + regex + "," + method);

                }
            }
        } catch (Exception e) {

        }
    }

    private void initThemeResolver(CApplicationContext context) {
    }

    private void initLocaleResolver(CApplicationContext context) {
    }

    private void initMultipartResolver(CApplicationContext context) {
    }
}

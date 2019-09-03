package cn.com.comline.study.studyspringmvc.springmvc.annotation;

import java.lang.annotation.*;

/**
 * <p>Package: cn.com.comline.study.studyspringmvc.codingspringmvc.annotation</p>
 * <p>ClassName: CController </p>
 * <p>description：</p>
 * <p>Copyright (c) www.island.ren 2019</p>
 * <p>author: comline@foxmail.com</p>
 * <p>create: 2019-07-10 14:08</p>
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CController {
    String value() default "";
}

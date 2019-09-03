package cn.com.comline.study.studyspringmvc.springmvc.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CRequestParam {
    String value() default "";
}

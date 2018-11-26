package com.platform.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by YangLD
 * @date 2018/7/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public  @interface CsvField {

    /** JSON属性映射名称 **/
    public String name() default "";

}

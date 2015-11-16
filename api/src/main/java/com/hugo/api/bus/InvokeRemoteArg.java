package com.hugo.api.bus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hq on 2015/11/15.
 * 目标是方法
 * 作用有效期是：runtime,要反射时可以获取该注解信息
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InvokeRemoteArg {
}

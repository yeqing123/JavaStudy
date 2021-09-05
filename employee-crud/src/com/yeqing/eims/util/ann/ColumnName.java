package com.yeqing.eims.util.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//贴在属性上的注解，用于标识与属性对应的在数据库表中的列名
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {
    String value();
}

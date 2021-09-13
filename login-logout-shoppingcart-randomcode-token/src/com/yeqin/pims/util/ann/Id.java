package com.yeqin.pims.util.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//用于标识对应的列是表的主键，如果是自动递增的就注明
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    IdType value() default IdType.AUTO_INCREMENT; //缺省值为自动递增
}

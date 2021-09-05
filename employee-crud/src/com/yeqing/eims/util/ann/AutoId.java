package com.yeqing.eims.util.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//贴在属性上，表示与该属性对应的表中的列是主键，默认是自动递增的
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoId {
       IdType value() default IdType.AUTO_INCREMENT;
}

//枚举类型，成员有两个：AUTO_INCREMENT表示对应主键是自动递增的，另一个表示为非自动递增
enum IdType {
	AUTO_INCREMENT, NON_AUTO_INCREMENT
}

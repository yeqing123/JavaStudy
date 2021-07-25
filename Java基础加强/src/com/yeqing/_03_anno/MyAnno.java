package com.yeqing._03_anno;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno {
    String name() default "李四";
    int age();
    String[] hobby() default {"A", "B", "C"};
}

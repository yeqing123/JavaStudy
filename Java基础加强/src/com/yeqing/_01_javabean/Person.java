package com.yeqing._01_javabean;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {
    private String name;
    private Long id;
    private int age;
    private Date bornDate;
}

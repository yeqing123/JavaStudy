package com.yeqing.smis.domain;



import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Student {
	private Long id;   // 学生信息的ID
    private String name; // 学生的姓名
    private Integer age;  // 学生的年龄

    @Override
    public String toString() {
    	return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}

package com.yeqing.jdbc.domain;

import com.yeqing.jdbc.ann.Column;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Student2 {
	private Long id;   // 学生信息的ID
	@Column("name")
    private String name22; // 学生的姓名
	@Column("age")
    private Integer age33;  // 学生的年龄

    @Override
    public String toString() {
    	return "Student2 [id=" + id + ", name=" + name22 + ", age=" + age33 + "]";
    }
}

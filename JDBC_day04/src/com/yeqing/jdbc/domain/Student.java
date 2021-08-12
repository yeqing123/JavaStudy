package com.yeqing.jdbc.domain;

import com.yeqing.jdbc.ann.Column;
import com.yeqing.jdbc.ann.Id;
import com.yeqing.jdbc.ann.Table;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Table("t_students")
public class Student {
	@Id
	private Long id;   // 学生信息的ID
	@Column("name22")
    private String name; // 学生的姓名
	@Column("age22")
    private Integer age;  // 学生的年龄

    @Override
    public String toString() {
    	return "Student2 [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}

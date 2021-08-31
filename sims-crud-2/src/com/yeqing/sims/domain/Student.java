package com.yeqing.sims.domain;

import com.yeqing.sims.ann.Column;
import com.yeqing.sims.ann.Id;
import com.yeqing.sims.ann.TableName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName("t_students")
public class Student {
	public Student() {}
    public Student(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
    @Id  // 添加该注释标识，该属性对应的列在数据库中是主键，并设置为自动递增，添加信息时无需手动添加
    @Column("s_id")
	private Long id;
    private String name;
    private Integer age;
}

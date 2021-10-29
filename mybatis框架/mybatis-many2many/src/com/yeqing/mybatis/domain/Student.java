package com.yeqing.mybatis.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//封装了student表中的学生信息
@Getter@Setter
@ToString(exclude="teachers")
public class Student {
	private Long id;   //学生ID
	private String name; //学生姓名
	private List<Teacher> teachers = new ArrayList<>();  //因为是“多对多”的关系，所以每个学生维护了与他相关的老师的集合
}

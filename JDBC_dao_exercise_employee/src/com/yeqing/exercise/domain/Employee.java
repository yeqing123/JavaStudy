package com.yeqing.exercise.domain;

import java.math.BigDecimal;

import com.yeqing.exercise.ann.Column;
import com.yeqing.exercise.ann.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Employee {
	@Id
	@Column("e_id")
    private Long id;
    private String name;
    private String job;
    private BigDecimal salary;
}

package com.yeqing.eims.domain;

import java.math.BigDecimal;

import com.yeqing.eims.util.ann.AutoId;
import com.yeqing.eims.util.ann.ColumnName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {
	@AutoId()
	@ColumnName("e_id")
    private Long id;
    private String name;
    private String job;
    private BigDecimal salary;
}

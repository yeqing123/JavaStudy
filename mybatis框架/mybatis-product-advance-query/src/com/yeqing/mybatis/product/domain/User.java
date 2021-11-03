package com.yeqing.mybatis.product.domain;

import lombok.Data;

//封装了t_user表中用户的信息
@Data
public class User {
	private Long id;
	private String username;
	private String password;
	private Boolean isManager;
}

package com.yeqing.mybatis.params.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

//方式一：封装登录操作的两个参数：用户名、密码
@AllArgsConstructor
@Getter
public class LoginVO {
	private String username;
	private String password;
}

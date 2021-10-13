package com.yeqing.mybatis.params.domain;

import lombok.Data;

//封装了用户信息（用户名、密码）
@Data
public class Client {
	private String username;
	private String password;
}

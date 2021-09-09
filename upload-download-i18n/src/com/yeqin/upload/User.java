package com.yeqin.upload;

import lombok.Data;

//封装了用户的注册信息
@Data
public class User {
	private String username;  
	private String email;
	private CFile imageInfo;
}

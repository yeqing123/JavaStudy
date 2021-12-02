package com.yeqing.mybatis.mapper;

import com.yeqing.domain.User;

public interface UserMapper {

	User checkLogin(String username, String password);
}

package com.yeqing.mapper.user;

import org.apache.ibatis.annotations.Param;

import com.yeqing.domain.User;

public interface UserMapper {

	User checkLogin(@Param("username")String username, @Param("password")String password);

	User get(String username);

	User get(String username, String password);
}

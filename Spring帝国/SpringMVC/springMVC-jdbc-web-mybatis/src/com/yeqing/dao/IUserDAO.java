package com.yeqing.dao;

import org.apache.ibatis.annotations.Param;

import com.yeqing.domain.User;

public interface IUserDAO {

	User checkLogin(String username, String password);

}

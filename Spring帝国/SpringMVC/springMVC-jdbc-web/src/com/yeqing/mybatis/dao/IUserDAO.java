package com.yeqing.mybatis.dao;

import com.yeqing.domain.User;

public interface IUserDAO {

	User checkLogin(String username, String password);

}

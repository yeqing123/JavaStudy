package com.yeqing.register.dao;

import com.yeqing.register.domain.User;

public interface IUserDAO {
	
	void save(User u) throws Exception;
}

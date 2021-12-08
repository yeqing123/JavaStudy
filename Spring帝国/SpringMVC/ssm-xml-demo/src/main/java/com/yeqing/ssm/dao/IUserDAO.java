package com.yeqing.ssm.dao;

import java.util.List;

import com.yeqing.ssm.domain.User;

public interface IUserDAO {
	void insert(User u);

	void deleteById(Long id);

	void updateById(User u);

	User selectById(Long id);

	List<User> selectALL();
}

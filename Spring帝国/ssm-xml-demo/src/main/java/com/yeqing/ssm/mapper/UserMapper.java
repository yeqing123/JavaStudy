package com.yeqing.ssm.mapper;

import java.util.List;

import com.yeqing.ssm.domain.User;

public interface UserMapper {
	int insert(User u);

	void deleteById(Long id);

	void updateById(User u);

	User selectById(Long id);

	List<User> selectAll();
}

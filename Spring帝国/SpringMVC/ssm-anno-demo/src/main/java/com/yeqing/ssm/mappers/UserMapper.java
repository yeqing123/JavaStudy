package com.yeqing.ssm.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yeqing.ssm.domain.User;

public interface UserMapper {
	int insert(User u);

	void deleteById(Long id);

	void updateById(User u);

	User selectById(Long id);

	List<User> selectAll();
	
	User checkLogin(@Param("name")String name, @Param("password")String password);
}

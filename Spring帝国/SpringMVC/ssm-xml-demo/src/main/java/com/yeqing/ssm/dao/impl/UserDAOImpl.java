package com.yeqing.ssm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeqing.ssm.dao.IUserDAO;
import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.mapper.UserMapper;

@Repository
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private UserMapper mapper;
	public int insert(User u) {
		return mapper.insert(u);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	public void updateById(User u) {
		mapper.updateById(u);
	}

	public User selectById(Long id) {
		return mapper.selectById(id);
	}

	public List<User> selectAll() {
		return mapper.selectAll();
	}

}

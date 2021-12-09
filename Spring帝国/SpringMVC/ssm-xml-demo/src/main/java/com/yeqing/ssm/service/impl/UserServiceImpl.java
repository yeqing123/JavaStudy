package com.yeqing.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeqing.ssm.dao.IUserDAO;
import com.yeqing.ssm.domain.User;
import com.yeqing.ssm.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO dao;
	public int save(User u) {
		int i = dao.insert(u);
		int a = 1/0;
		return i;
	}

	public void delete(Long id) {
		dao.deleteById(id);
	}

	public void update(User u) {
		dao.updateById(u);
	}

	public User get(Long id) {
		return dao.selectById(id);
	}

	public List<User> listAll() {
		return dao.selectAll();
	}

}

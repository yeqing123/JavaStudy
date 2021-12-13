package com.yeqing.ssm.service;

import java.util.List;

import com.yeqing.ssm.domain.User;

public interface IUserService {

	void save(User u);

	void delete(Long id);

	void update(User u);

	User get(Long id);

	List<User> listAll();
}

package com.yeqing.service;


import com.yeqing.domain.User;

public interface IUserService {
    
	User checkLogin(String username, String password);
	
}

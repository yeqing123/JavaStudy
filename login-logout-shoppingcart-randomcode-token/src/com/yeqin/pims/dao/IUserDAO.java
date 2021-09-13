package com.yeqin.pims.dao;

import com.yeqin.pims.domain.User;

public interface IUserDAO {
    /**
     * 检查用户输入的登录账户是否存在
     * @param username 用户在登录页面输入的账户
     * @return 如果该账户存在则返回对应User对象，否则返回null
     */
	User checkLogin(String username);
}

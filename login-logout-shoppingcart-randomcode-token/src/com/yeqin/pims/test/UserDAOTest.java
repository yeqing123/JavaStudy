package com.yeqin.pims.test;


import org.junit.Test;

import com.yeqin.pims.dao.IUserDAO;
import com.yeqin.pims.dao.impl.UserDAOImpl;

public class UserDAOTest {
    private IUserDAO dao = new UserDAOImpl();
	@Test
	public void testCheckLogin() {
		System.out.println(dao.checkLogin("admin"));
	}

}

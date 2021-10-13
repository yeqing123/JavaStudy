package com.yeqing.mybatis.params.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yeqing.mybatis.params.domain.Client;
import com.yeqing.mybatis.params.domain.LoginVO;

//接口ClientMapper，定义了数据可操作方法
public interface ClientMapper {
	Client login1(LoginVO vo);

	Client login2(HashMap<String, Object> hashMap);

	Client login3(@Param("username")String string, @Param("password")String string2);
	
	List<Client> listAll(String param);
}

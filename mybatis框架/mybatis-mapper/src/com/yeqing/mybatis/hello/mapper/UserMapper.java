package com.yeqing.mybatis.hello.mapper;

import java.util.List;

import com.yeqing.mybatis.hello.domain.User;

//创建一个UserMapper接口，注意它里面的方法名必须与UserMapper.xml文件中的操作名称相同
public interface UserMapper {
    void insert(User u);
    
    void delete(Long id);
    
    void update(User u);
    
    User get(Long id);
    
    List<User> listAll();
}

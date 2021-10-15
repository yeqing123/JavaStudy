package com.yeqing.mybatis.ann.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yeqing.mybatis.ann.domain.User;

public interface UserMapper {
	//我们在SQL中给列名设置别名，来模拟列名与属性名不一致的情况，来看看使用注解是如何解决该问题的：
    @Select("SELECT id AS u_id,name AS u_name,salary AS u_salary FROM t_user WHERE id = #{id}")
    @Results(id="BaseResultMapper",value = {
    		@Result(column = "u_id",property = "id"),
    		@Result(column = "u_name",property = "name"),
    		@Result(column = "u_salary",property = "salary"),
    })
	User get(long l);

    @Select("SELECT id AS u_id,name AS u_name,salary AS u_salary FROM t_user")
    //使用@ResultMap可以重复使用已经定义过的@Results注解
    @ResultMap("BaseResultMapper")
	List<User> listAll();

    @Insert("INSERT INTO t_user (name,salary) VALUES (#{name},#{salary})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
	void insert(User u);

    @Delete("DELETE FROM t_user WHERE id = #{id}")
	void delete(long l);

    @Update("UPDATE t_user SET name=#{name},salary=#{salary} WHERE id=#{id}")
	void update(User u);

}

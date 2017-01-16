package com.fzk.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.fzk.entity.User;

public interface UserMapper {

	@Insert("INSERT INTO tb_user(username, password) VALUES(#{username},#{password})")
	public int insertUser(@Param("username") String username,
			@Param("password") String password);

	public int insertUserWithBackId(User user);
}

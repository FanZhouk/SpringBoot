package com.fzk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fzk.entity.User;
import com.fzk.mapper.UserMapper;

@Repository
public class UserDao {
	@Autowired
	private UserMapper userMapper;

	public int insertUser(String username, String password) {
		return userMapper.insertUser(username, password);
	}
	
	public int insertUserWithBackId(User user){
		return userMapper.insertUserWithBackId(user);
	}

	public List<User> findAll() {
		
		return userMapper.findAll();
	}
}

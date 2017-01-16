package com.fzk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fzk.entity.User;
import com.fzk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/addUser")
	public boolean addUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println("===姓名：" + username);
		System.out.println("===密码：" + password);
		return userService.addUser(username, password);
	}

	@RequestMapping(value = "/addUserWithBackId")
	public User addUserWithBackId(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		return userService.addUserWithBackId(username, password);
	}
}

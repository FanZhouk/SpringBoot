package com.fzk.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fzk.entity.User;
import com.fzk.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询所有用户信息（直接显示Json格式）
	 */
	@RequestMapping("getJson")
	public String getJson(Model model) {
		List<User> list = userService.findAll();
		String json = JSON.toJSONString(list);
		model.addAttribute("data", json);
		model.addAttribute("name", "abc");
		return "hello";
	}

	/**
	 * 查询所有用户信息（以表格显示）
	 */
	@RequestMapping("getData")
	public String getData(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("data", list);
		model.addAttribute("name", "abc");
		return "data";
	}

	@RequestMapping(value = "/addUser")
	@ResponseBody
	public String addUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println("===姓名：" + username);
		System.out.println("===密码：" + password);
		return username + "---" + password;
	}

	@RequestMapping(value = "/addUserWithBackId")
	@ResponseBody
	public User addUserWithBackId(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		return userService.addUserWithBackId(username, password);
	}
}

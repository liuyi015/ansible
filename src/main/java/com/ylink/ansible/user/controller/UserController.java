package com.ylink.ansible.user.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ylink.ansible.user.pojo.User;
import com.ylink.ansible.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String allUser(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		List allUser = userService.getAllUser(cookies);
		if(allUser==null) {
			request.setAttribute("error", "请重新登录！！！");
			return "login";
		}
		request.setAttribute("userList", allUser);
		return "user/user";
	}

	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "user/add";
	}
	
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("user") User user,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		userService.addUser(user,cookies);
		return allUser(request);
	}
}

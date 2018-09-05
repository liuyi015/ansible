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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		return "user/list";
	}

	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "user/add";
	}
	
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("user") User user,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		if("system_administrator".equals(user.getUser_type())) {
			user.setIs_superuser(true);
		}else if("system_auditor".equals(user.getUser_type())) {
			user.setIs_system_auditor(true);
		}
		userService.addUser(user,cookies);
		return allUser(request);
	}
	
	@RequestMapping(value="/toEdit",method=RequestMethod.GET)
	public String toEdit(Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		User user=userService.findById(id,cookies);
		if(user!=null) {
			if(user.getIs_superuser()) {
				user.setUser_type("system_administrator");
			}else if(user.getIs_system_auditor()) {
				user.setUser_type("system_auditor");
			}else {
				user.setUser_type("normal");
			}
		}
		
		request.setAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping(value="/doEdit",method=RequestMethod.POST)
	public String doEdit(@ModelAttribute("user") User user,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		if("system_administrator".equals(user.getUser_type())) {
			user.setIs_superuser(true);
		}else if("system_auditor".equals(user.getUser_type())) {
			user.setIs_system_auditor(true);
		}
		String rs=userService.UpdateUser(user,cookies);
		if(rs==null) {
			request.getSession().setAttribute("msg", "修改失败！！！");
			
		}else {
			request.getSession().setAttribute("msg", "修改成功！！！");
		}
		request.setAttribute("user", user);
		attr.addAttribute("id",user.getId());
		return "redirect:/user/toEdit";
	}
	
	@RequestMapping(value="/toDelete")
	public String toDelete(Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		String rs=userService.deleteById(id,cookies);
		if(rs==null) {
			request.getSession().setAttribute("msg", "删除失败！！！");
			
		}else {
			request.getSession().setAttribute("msg", "删除成功！！！");
		}
		return "redirect:/user/all";
	}
}

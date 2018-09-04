package com.ylink.ansible.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ylink.ansible.login.pojo.TokenResult;
import com.ylink.ansible.login.service.LoginService;
import com.ylink.ansible.user.pojo.User;
import com.ylink.ansible.utils.CookieUtils;

import net.sf.json.JSONObject;

@Controller
public class LoginController {
	
	@Autowired
	public LoginService loginService;
	
	@Value("${API_URL}")
	private String API_URL;
	
	@RequestMapping("/login")
	public String login() {
		System.out.println(API_URL);
		return "login";
	}
	
	@RequestMapping("/main")
	public String index() {
		return "main";
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String doLogin(User user ,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String token = loginService.login(user);
		if(StringUtils.isEmpty(token)) {
			request.setAttribute("error", "账号密码错误！！！！！");
			return this.login();
		}
		
		TokenResult tokenResult = (TokenResult) JSONObject.toBean(JSONObject.fromObject(token),TokenResult.class);
		System.out.println("getToken():"+tokenResult.getToken());
		System.out.println("getExpires():"+tokenResult.getExpires());
		CookieUtils.setCookie(request, response, "token", tokenResult.getToken());
		CookieUtils.setCookie(request, response, "token_expires", tokenResult.getExpires());
		
		request.setAttribute("tokenResult", tokenResult);
		request.getSession().setAttribute("userSession", user);
		return "main";
	}
	
	@RequestMapping(value="/doMe",method=RequestMethod.GET)
	public String doMe(HttpServletRequest request) throws Exception {
		Cookie[] cookies=request.getCookies();
		String rs = loginService.aboutMe(cookies);
		request.setAttribute("me", rs);
		return "me";
	}
}

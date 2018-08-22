package com.ylink.ansible.login.service;


import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.user.pojo.User;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONObject;


@Service
public class LoginService {
	
	@Value("${API_URL}")
	private String API_URL;

	public String login(User user) throws Exception {
		
		JSONObject userJson = JSONObject.fromObject(user);   //转化为json数据
		
		String url=API_URL+"/authtoken/";   
	
		String rs = HttpRequestUtils.sendHttpsRequestByPost(url, userJson);
		
		System.out.println(rs);
		
		return rs;
	}

	public String aboutMe(Cookie[] cookies) throws Exception {
		String url=API_URL+"/me"; 
		Cookie tokenCookie = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByGet(url, tokenCookie);
		System.out.println(rs);
		return rs;
	}
}

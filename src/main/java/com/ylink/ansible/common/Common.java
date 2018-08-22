package com.ylink.ansible.common;

import javax.servlet.http.Cookie;

public class Common {

	/*
	 * 从cookies中获取token
	 */
	public static Cookie getToken(Cookie[] cookies) {
		Cookie tokenCookie = null;
		for (Cookie cookie : cookies) {
		    switch(cookie.getName()){
		        case "token":
		            tokenCookie=cookie;
		            break;
		        default:
		            break;
		    }
		}
		System.out.println("从cookies中取出的token:"+tokenCookie.getValue());
		return tokenCookie;
	}
}

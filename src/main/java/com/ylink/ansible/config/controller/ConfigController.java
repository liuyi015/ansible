package com.ylink.ansible.config.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylink.ansible.config.pojo.Config;
import com.ylink.ansible.config.service.ConfigService;

import net.sf.json.JSONObject;

@RequestMapping("/config")
@Controller
public class ConfigController {
	
	@Autowired
	public ConfigService configService;
	
	@RequestMapping(value="/getConfig",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getConfig(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Config config = configService.getConfig(cookies);
		
		return JSONObject.fromObject(config).toString();
	}

}

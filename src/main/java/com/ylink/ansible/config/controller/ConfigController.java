package com.ylink.ansible.config.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ylink.ansible.config.pojo.Config;
import com.ylink.ansible.config.service.ConfigService;
import com.ylink.ansible.project.pojo.Project;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RequestMapping("/config")
@Controller
public class ConfigController {
	
	@Autowired
	public ConfigService configService;
	
	@RequestMapping("/getConfig")
	@ResponseBody
	public String getConfig(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		Config config = configService.getConfig(cookies);
		return JSONObject.fromObject(config).toString();
	}

}

package com.ylink.ansible.config.service;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.config.pojo.Config;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONObject;

@Service
public class ConfigService {
	@Value("${API_URL}")
	private String API_URL;

	public Config getConfig(Cookie[] cookies) throws Exception {
		Config config=null;
		Cookie token = Common.getToken(cookies);
		
		String apiUrl=API_URL+"/config/";
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		if(rs!=null) {
			 config = (Config) JSONObject.toBean(JSONObject.fromObject(rs), Config.class);
		}
		return config;
	}

}

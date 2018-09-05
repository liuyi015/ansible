package com.ylink.ansible.host.service;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.group.pojo.Group;
import com.ylink.ansible.host.pojo.Host;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONObject;

@Service
public class HostService {
	
	@Value("${API_URL}")
	private String API_URL;

	public String addHost(Host host, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/hosts/";
		Cookie token = Common.getToken(cookies);
		
		JSONObject json = JSONObject.fromObject(host);
		String rs = HttpRequestUtils.sendHttpsRequestByPostAndCookie(apiUrl, json, token);
		
		return rs;
	}

	public Host findById(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/hosts/"+id;
		Cookie token = Common.getToken(cookies);
		Host host =null;
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		if(rs!=null) {
			host= (Host) JSONObject.toBean(JSONObject.fromObject(rs),Host.class);
		}
		return host;
	}

	public Host updateGroup(Host host, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/hosts/"+host.getId()+"/";
		Cookie token = Common.getToken(cookies);
		JSONObject json = JSONObject.fromObject(host);
		String rs = HttpRequestUtils.sendHttpsRequestByPutAndCookie(apiUrl, json, token);
		if(rs!=null) {
			host= (Host) JSONObject.toBean(JSONObject.fromObject(rs),Host.class);
			return host;
		}
		return null;
	}

	public String deleteById(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/hosts/"+id+"/";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return rs;
	}

}

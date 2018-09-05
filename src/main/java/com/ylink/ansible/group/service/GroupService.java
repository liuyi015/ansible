package com.ylink.ansible.group.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.group.pojo.Group;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class GroupService {

	@Value("${API_URL}")
	private String API_URL;
	
	public String addGroup(Group group, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/groups/";
		Cookie token = Common.getToken(cookies);
		
		JSONObject json = JSONObject.fromObject(group);
		String rs = HttpRequestUtils.sendHttpsRequestByPostAndCookie(apiUrl, json, token);
		
		return rs;
	}

	public Group findById(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/groups/"+id;
		Cookie token = Common.getToken(cookies);
		Group group =null;
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		if(rs!=null) {
			group= (Group) JSONObject.toBean(JSONObject.fromObject(rs),Group.class);
		}
		return group;
	}

	public Group updateGroup(Group group, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/groups/"+group.getId()+"/";
		Cookie token = Common.getToken(cookies);
		JSONObject json = JSONObject.fromObject(group);
		String rs = HttpRequestUtils.sendHttpsRequestByPutAndCookie(apiUrl, json, token);
		if(rs!=null) {
			group= (Group) JSONObject.toBean(JSONObject.fromObject(rs),Group.class);
			return group;
		}
		return null;
	}

	public String deleteById(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/groups/"+id+"/";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return rs;
	}

}

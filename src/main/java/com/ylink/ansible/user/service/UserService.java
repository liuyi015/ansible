package com.ylink.ansible.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.user.pojo.User;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class UserService {
	
	String baseUrl="https://172.168.65.88/api/v1";

	public List getAllUser(Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=baseUrl+"/users";
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		if(StringUtils.isEmpty(rs)) {
			return null;
		}
		JSONObject object = JSONObject.fromObject(rs);
		JSONArray jsonArray=object.getJSONArray("results");
		Iterator<JSONArray> iterator = jsonArray.iterator();
		List userLists=new ArrayList<>();
		while(iterator.hasNext()) {
			User user = (User) JSONObject.toBean(JSONObject.fromObject(iterator.next()), User.class);
			userLists.add(user);
		}
		System.out.println(userLists.toString());
		return userLists;
	}

	public String addUser(User user, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=baseUrl+"/users/";
		JSONObject userJson = JSONObject.fromObject(user);
		String rs = HttpRequestUtils.sendHttpsRequestByPostAndCookie(apiUrl, userJson, token);
		return rs;
	}

	public User findById(Integer id, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=baseUrl+"/users/"+id+"/";
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		if(rs!=null) {
			User user = (User) JSONObject.toBean(JSONObject.fromObject(rs), User.class);
			return user;
		}
		return null;
	}

	public String UpdateUser(User user, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=baseUrl+"/users/"+user.getId()+"/";
		JSONObject userJson = JSONObject.fromObject(user);
		if(StringUtils.isEmpty(user.getPassword())) {
			userJson.remove("password");
		}
		String rs = HttpRequestUtils.sendHttpsRequestByPutAndCookie(apiUrl, userJson, token);
		return rs;
	}

	public String deleteById(Integer id, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=baseUrl+"/users/"+id+"/";
		String rs = HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return rs;
	}

}

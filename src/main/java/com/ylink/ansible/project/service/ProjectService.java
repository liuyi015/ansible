package com.ylink.ansible.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.user.pojo.User;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ProjectService {
	
	@Value("${API_URL}")
	private String API_URL;
	
	public List<Project> toList(Cookie[] cookie) throws Exception {
		String url=API_URL+"/projects";
		Cookie token = Common.getToken(cookie);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		JSONArray results = JSONObject.fromObject(result).getJSONArray("results");
		List<Project> list=new ArrayList<>();
		Iterator<Project> it = results.iterator();
		while(it.hasNext()) {
			Project project = (Project) JSONObject.toBean(JSONObject.fromObject(it.next()), Project.class);
			list.add(project);
		}
		
		System.out.println(list.toString());
		return list;
	}

	public String addProject(Project project, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/projects/";            //post后面的"/"不能省略，会引起重定向
		JSONObject userJson = JSONObject.fromObject(project);
		
//		测试数据
//		userJson=JSONObject.fromObject("{\"name\":\"2\",\"description\":\"\",\"organization\":1,\"scm_type\":\"git\",\"base_dir\":\"/var/lib/awx/projects\",\"scm_url\":\"https://github.com/ansible/ansible-tower-samples\",\"scm_branch\":\"\",\"credential\":null,\"scm_clean\":false,\"scm_delete_on_update\":false,\"scm_update_on_launch\":false,\"scm_update_cache_timeout\":0}");
		
		String rs = HttpRequestUtils.sendHttpsRequestByPostAndCookie(apiUrl, userJson,token);
		
		System.out.println(rs);
		return rs;
		
	}

	/*
	 * 根据id查找project
	 * 
	 */
	public Project findById(Integer id, Cookie[] cookies) throws Exception {
		Project project=null;
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/projects/"+id+"/";
		String rs=HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		if(StringUtils.isNotEmpty(rs)) {
			project=(Project) JSONObject.toBean(JSONObject.fromObject(rs), Project.class);
		}
		return project;
	}

	public Project UpdateProject(Project project, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/projects/"+project.getId()+"/";
		JSONObject jsonObject = JSONObject.fromObject(project);
		String rs=HttpRequestUtils.sendHttpsRequestByPutAndCookie(apiUrl, jsonObject, token);
		Project poj=null;
		if(StringUtils.isNotEmpty(rs)) {
			poj=(Project) JSONObject.toBean(JSONObject.fromObject(rs), Project.class);
		}
		return poj;
	}

	public String deleteProject(String id, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/projects/"+id+"/";
		String rs=HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return null;
	}


}

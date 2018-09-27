package com.ylink.ansible.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.common.ResultInfo;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.user.pojo.User;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ProjectService {
	
	@Value("${API_URL}")
	private String API_URL;
	@Value("${PAGE_SIZE}")
	private int PAGE_SIZE;
	
	public ResultInfo toList(Map<String, Object> params,Cookie[] cookie) throws Exception {
		//拼接url，获取get返回值
		String result = getResult(params, cookie);
		//截取结果集
		JSONArray results = JSONObject.fromObject(result).getJSONArray("results");
		List<Project> list=new ArrayList<>();
		Iterator<Project> it = results.iterator();
		while(it.hasNext()) {
			Project project = (Project) JSONObject.toBean(JSONObject.fromObject(it.next()), Project.class);
			list.add(project);
		}
		//截取总数量
		int count = JSONObject.fromObject(result).getInt("count");
		
		ResultInfo resultInfo=new ResultInfo();
		resultInfo.setCount(count);
		int totalPage=(count+PAGE_SIZE-1)/PAGE_SIZE;
		resultInfo.setTotalPage(totalPage);
		resultInfo.setList(list);
		
		System.out.println(list.toString());
		return resultInfo;
	}
	/**
	 * 根据条件查询project
	 * @param params
	 * @param cookie
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Project> getAllProject(Cookie[] cookie) throws Exception {
		//拼接url，获取get返回值
		String result = getResult(null, cookie);
		//截取结果集
		JSONArray results = JSONObject.fromObject(result).getJSONArray("results");
		List<Project> list=new ArrayList<>();
		Iterator<Project> it = results.iterator();
		while(it.hasNext()) {
			Project project = (Project) JSONObject.toBean(JSONObject.fromObject(it.next()), Project.class);
			list.add(project);
		}
		return list;
	}

	/**
	 * 拼接url，获取get返回值
	 * @param params
	 * @param cookie
	 * @return
	 * @throws Exception
	 */
	public String getResult(Map<String, Object> params ,Cookie[] cookie) throws Exception {
		String url=API_URL+"/projects/?page_size="+PAGE_SIZE+"&order_by=name";
		if(params!=null) {
			//拼接url
			for(Entry<String, Object> entry:params.entrySet()) {
				url=url+"&"+entry.getKey()+"="+entry.getValue();
			}
		}
		Cookie token = Common.getToken(cookie);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		return result;
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
		return rs;
	}
	/**
	 * 根据project 的id查找project 对应的playbook
	 * @param id
	 * @param cookies
	 * @return
	 * @throws Exception 
	 */

	public String getPlaybook(Integer id, Cookie[] cookies) throws Exception {
		String url=API_URL+"/projects/"+id +"/playbooks/";
		Cookie token = Common.getToken(cookies);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		System.out.println(result);
		return result;
	}


}

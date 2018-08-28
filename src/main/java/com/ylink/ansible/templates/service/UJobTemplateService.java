package com.ylink.ansible.templates.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.templates.pojo.Template;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class UJobTemplateService {

	@Value("${API_URL}")
	private String API_URL;
	
	public List<Template> toList(Cookie[] cookie) throws Exception {
		String url=API_URL+"/unified_job_templates/?page_size=20&order_by=name&type=workflow_job_template,job_template";
		Cookie token = Common.getToken(cookie);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		JSONArray results = JSONObject.fromObject(result).getJSONArray("results");
		List<Template> list=new ArrayList<>();
		Iterator<Template> it = results.iterator();
		while(it.hasNext()) {
			Template template = (Template) JSONObject.toBean(JSONObject.fromObject(it.next()), Template.class);
			list.add(template);
		}
		
		System.out.println(list.toString());
		return list;
	}

	public String addTemplate(Template template, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/job_templates/";            //post后面的"/"不能省略，会引起重定向
		JSONObject json = JSONObject.fromObject(template);
		
		String rs = HttpRequestUtils.sendHttpsRequestByPostAndCookie(apiUrl, json,token);
		
		System.out.println(rs);
		return rs;
	}

	public Template findById(Integer id, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/job_templates/"+id+"/";    
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		Template template = (Template) JSONObject.toBean(JSONObject.fromObject(rs), Template.class);
		return template;
	}

	public String UpdateProject(Template template, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/job_templates/"+template.getId()+"/";    
		JSONObject json = JSONObject.fromObject(template);
		String rs = HttpRequestUtils.sendHttpsRequestByPutAndCookie(apiUrl, json, token);
		return rs;
	}

	public String deleteProject(String id, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String apiUrl=API_URL+"/job_templates/"+id+"/";    
		String rs = HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return rs;
	}

}

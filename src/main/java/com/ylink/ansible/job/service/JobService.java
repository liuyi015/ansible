package com.ylink.ansible.job.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.inventory.pojo.Inventory;
import com.ylink.ansible.inventory.pojo.InventoryUpdate;
import com.ylink.ansible.job.pojo.Job;
import com.ylink.ansible.job.pojo.SystemJob;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.project.pojo.ProjectUpdate;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
public class JobService {

	@Value("${API_URL}")
	private String API_URL;
	
	public List findUnifiedJob(Map<String, Object> params, Cookie[] cookies) throws Exception {
		String url=API_URL+"/unified_jobs/";
		
		List<Object> list=new ArrayList<>();
		if(params!=null) {
			//拼接url
			boolean isFirst=true;
			for(Entry<String, Object> entry:params.entrySet()) {
				if(isFirst) {
					url=url+"?"+entry.getKey()+"="+entry.getValue();
					isFirst=false;
				}else {
					url=url+"&"+entry.getKey()+"="+entry.getValue();
				}
			}
		}
		Cookie token = Common.getToken(cookies);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		JSONArray results = JSONObject.fromObject(result).getJSONArray("results");
		Iterator<?> it = results.iterator();
		while(it.hasNext()) {
			Object object = it.next();
			//获取Object类型
			String type = JSONObject.fromObject(object).getString("type");
			
			if("job".equals(type)) {
				Job job = (Job) JSONObject.toBean(JSONObject.fromObject(object), Job.class);
				list.add(job);
			}else if("system_job".equals(type)) {
				SystemJob systemJob = (SystemJob) JSONObject.toBean(JSONObject.fromObject(object), SystemJob.class);
				list.add(systemJob);
			}else if("inventory_update".equals(type)) {
				InventoryUpdate inventoryUpdate = (InventoryUpdate) JSONObject.toBean(JSONObject.fromObject(object), InventoryUpdate.class);
				list.add(inventoryUpdate);
			}else if("project_update".equals(type)) {
				ProjectUpdate projectUpdate = (ProjectUpdate) JSONObject.toBean(JSONObject.fromObject(object), ProjectUpdate.class);
				list.add(projectUpdate);
			}
		}
		
		return list;
	}

	public String deleteJob(String id, String type, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/"+type+"s/"+id+"/";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return rs;
	}

	public Object findByIdAndType(String id, String type, Cookie[] cookies) throws Exception {
		String url=API_URL+"/"+type+"s/"+id+"/";
		Cookie token = Common.getToken(cookies);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(result==null) {
			return null;
		}
		//获取Object类型
		String ObType = JSONObject.fromObject(result).getString("type");
		
		if("job".equals(type)) {
			Job job = (Job) JSONObject.toBean(JSONObject.fromObject(result), Job.class);
			return job;
		}else if("system_job".equals(type)) {
			SystemJob systemJob = (SystemJob) JSONObject.toBean(JSONObject.fromObject(result), SystemJob.class);
			return systemJob;
		}else if("inventory_update".equals(type)) {
			InventoryUpdate inventoryUpdate = (InventoryUpdate) JSONObject.toBean(JSONObject.fromObject(result), InventoryUpdate.class);
			return inventoryUpdate;
		}else if("project_update".equals(type)) {
			ProjectUpdate projectUpdate = (ProjectUpdate) JSONObject.toBean(JSONObject.fromObject(result), ProjectUpdate.class);
			return projectUpdate;
		}else {
			return null;
		}
		
	}

	public Object findByUrl(String url, Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(result==null) {
			return null;
		}
		//获取Object类型
		String type = JSONObject.fromObject(result).getString("type");
		
		if("job".equals(type)) {
			Job job = (Job) JSONObject.toBean(JSONObject.fromObject(result), Job.class);
			return job;
		}else if("system_job".equals(type)) {
			SystemJob systemJob = (SystemJob) JSONObject.toBean(JSONObject.fromObject(result), SystemJob.class);
			return systemJob;
		}else if("inventory_update".equals(type)) {
			InventoryUpdate inventoryUpdate = (InventoryUpdate) JSONObject.toBean(JSONObject.fromObject(result), InventoryUpdate.class);
			return inventoryUpdate;
		}else if("project_update".equals(type)) {
			ProjectUpdate projectUpdate = (ProjectUpdate) JSONObject.toBean(JSONObject.fromObject(result), ProjectUpdate.class);
			return projectUpdate;
		}else {
			return null;
		}
	}

}


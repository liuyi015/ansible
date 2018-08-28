package com.ylink.ansible.inventory.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.inventory.pojo.Inventory;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class InventoryService {
	
	@Value("${API_URL}")
	private String API_URL;

	public List<Inventory> getAllInventory(Cookie[] cookies) throws Exception {
		String url=API_URL+"/inventories/";
		Cookie token = Common.getToken(cookies);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		JSONArray results = JSONObject.fromObject(result).getJSONArray("results");
		List<Inventory> list=new ArrayList<>();
		Iterator<Project> it = results.iterator();
		while(it.hasNext()) {
			Inventory inventory = (Inventory) JSONObject.toBean(JSONObject.fromObject(it.next()), Inventory.class);
			list.add(inventory);
		}
		
		System.out.println(list.toString());
		return list;
	}

	public String addInventory(Inventory inventory, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/";
		Cookie token = Common.getToken(cookies);
		JSONObject json = JSONObject.fromObject(inventory);
		String rs = HttpRequestUtils.sendHttpsRequestByPostAndCookie(apiUrl, json, token);
		return rs;
	}

	public Inventory getInventoryById(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/"+id+"/";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		Inventory inventory=null;
		if(StringUtils.isNotEmpty(rs)) {
			 inventory = (Inventory) JSONObject.toBean(JSONObject.fromObject(rs), Inventory.class);
		}
		return inventory;
	}

	public Inventory updateInventory(Inventory inventory, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/"+inventory.getId()+"/";
		Cookie token = Common.getToken(cookies);
		JSONObject json = JSONObject.fromObject(inventory);
		String rs = HttpRequestUtils.sendHttpsRequestByPutAndCookie(apiUrl, json, token);
		Inventory in=null;
		if(StringUtils.isNotEmpty(rs)) {
			 in = (Inventory) JSONObject.toBean(JSONObject.fromObject(rs), Inventory.class);
		}
		return in;
	}

	public String deleteById(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/"+id+"/";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return rs;
	}

}

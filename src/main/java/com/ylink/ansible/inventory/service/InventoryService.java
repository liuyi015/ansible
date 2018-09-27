package com.ylink.ansible.inventory.service;

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
import com.ylink.ansible.group.pojo.Group;
import com.ylink.ansible.host.pojo.Host;
import com.ylink.ansible.inventory.pojo.Inventory;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class InventoryService {
	
	@Value("${API_URL}")
	private String API_URL;
	@Value("${PAGE_SIZE}")
	private int PAGE_SIZE;

	/**
	 * 查询所有的Inventory
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public List<Inventory> getAllInventory(Cookie[] cookies) throws Exception {
		String result =this.getResult(null, cookies);
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

	/**
	 * 添加Inventory
	 * @param inventory
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public String addInventory(Inventory inventory, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/";
		Cookie token = Common.getToken(cookies);
		JSONObject json = JSONObject.fromObject(inventory);
		String rs = HttpRequestUtils.sendHttpsRequestByPostAndCookie(apiUrl, json, token);
		return rs;
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 更新Inventory
	 * @param inventory
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 根据ID删除Inventory
	 * @param id
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public String deleteById(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/"+id+"/";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByDelete(apiUrl, token);
		return rs;
	}

	/**
	 * 根据条件查询Inventory
	 * @param params
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public ResultInfo toList(Map<String, Object> params, Cookie[] cookies) throws Exception {
		
		String result =this.getResult(params, cookies);
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
	 * 拼接url，获取get返回值
	 * @param params
	 * @param cookie
	 * @return
	 * @throws Exception
	 */
	public String getResult(Map<String, Object> params ,Cookie[] cookies) throws Exception {
		String url=API_URL+"/inventories/?page_size="+PAGE_SIZE+"&order_by=name";
		if(params!=null) {
			//拼接url
			for(Entry<String, Object> entry:params.entrySet()) {
				url=url+"&"+entry.getKey()+"="+entry.getValue();
			}
		}
		Cookie token = Common.getToken(cookies);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		return result;
	}

	public List<Group> getRootGroups(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/"+id+"/root_groups/?order_by=name";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		
		List<Group> list=new ArrayList<>();
		if(StringUtils.isEmpty(rs)) {
			return null;
		}
		JSONArray results = JSONObject.fromObject(rs).getJSONArray("results");
		Iterator<Project> it = results.iterator();
		while(it.hasNext()) {
			Group group = (Group) JSONObject.toBean(JSONObject.fromObject(it.next()), Group.class);
			list.add(group);
		}
		
		return list;
	}

	public List<Host> gethosts(Integer id, Cookie[] cookies) throws Exception {
		String apiUrl=API_URL+"/inventories/"+id+"/hosts/?order_by=name";
		Cookie token = Common.getToken(cookies);
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		
		List<Host> list=new ArrayList<>();
		if(StringUtils.isEmpty(rs)) {
			return null;
		}
		JSONArray results = JSONObject.fromObject(rs).getJSONArray("results");
		Iterator<Project> it = results.iterator();
		while(it.hasNext()) {
			Host host = (Host) JSONObject.toBean(JSONObject.fromObject(it.next()), Host.class);
			list.add(host);
		}
		
		return list;
	}

}

package com.ylink.ansible.organization.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ylink.ansible.common.Common;
import com.ylink.ansible.config.pojo.Config;
import com.ylink.ansible.credential.pojo.Credential;
import com.ylink.ansible.organization.pojo.Organization;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
public class OrganizationService {

	@Value("${API_URL}")
	private String API_URL;
	
	public List<Organization> getOrgan(Cookie[] cookies) throws Exception {
		Cookie token = Common.getToken(cookies);
		
		Organization organ=null;
		
		String apiUrl=API_URL+"/organizations/";
		String rs = HttpRequestUtils.sendHttpsRequestByGet(apiUrl, token);
		JSONArray results = JSONObject.fromObject(rs).getJSONArray("results");
		List<Organization> list=new ArrayList<>();
		Iterator<Organization> it = results.iterator();
		while(it.hasNext()) {
			Organization organization = (Organization) JSONObject.toBean(JSONObject.fromObject(it.next()), Organization.class);
			list.add(organization);
		}
		return list;
	}

}

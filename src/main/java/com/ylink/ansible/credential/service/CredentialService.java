package com.ylink.ansible.credential.service;

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
import com.ylink.ansible.credential.pojo.Credential;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.utils.HttpRequestUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CredentialService {
	
	@Value("${API_URL}")
	private String API_URL;

	public List<Credential> findCredential(Map<String, String> params, Cookie[] cookies) throws Exception {
		String url=API_URL+"/credentials/";
		if(params.isEmpty()) {
			return null;
		}
		//拼接url
		boolean isFirst=true;
		for(Entry<String, String> entry:params.entrySet()) {
			if(isFirst) {
				url=url+"?"+entry.getKey()+"="+entry.getValue();
				isFirst=false;
			}else {
				url=url+"&"+entry.getKey()+"="+entry.getValue();
			}
			
		}
		
		Cookie token = Common.getToken(cookies);
		String result = HttpRequestUtils.sendHttpsRequestByGet(url, token);
		if(StringUtils.isEmpty(result)) {
			return null;
		}
		JSONArray results = JSONObject.fromObject(result).getJSONArray("results");
		List<Credential> list=new ArrayList<>();
		Iterator<Credential> it = results.iterator();
		while(it.hasNext()) {
			Credential credential = (Credential) JSONObject.toBean(JSONObject.fromObject(it.next()), Credential.class);
			list.add(credential);
		}
		
		System.out.println(list.toString());
		return list;
	}

}

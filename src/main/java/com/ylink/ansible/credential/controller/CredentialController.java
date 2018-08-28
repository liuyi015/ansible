package com.ylink.ansible.credential.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylink.ansible.credential.pojo.Credential;
import com.ylink.ansible.credential.service.CredentialService;

import net.sf.json.JSONArray;

@RequestMapping("/credential")
@Controller
public class CredentialController {
	@Autowired
	public CredentialService credentialService;

	/**
	 * 查询project（返回json）
	 * @param request
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/getCredentialByKind",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getCredentialByKind(String kind,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Map<String,String> params=new HashMap<>();
		if(StringUtils.isEmpty(kind)) {
			return null;
		}
		params.put("kind", kind);
		List<Credential> list = credentialService.findCredential(params,cookies);
		JSONArray arr = JSONArray.fromObject(list);
		return arr.toString();
	}
}

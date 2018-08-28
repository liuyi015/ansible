package com.ylink.ansible.organization.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylink.ansible.organization.pojo.Organization;
import com.ylink.ansible.organization.service.OrganizationService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/organization")
@Controller
public class OrganizationController {
	@Autowired
	public OrganizationService organizationService;
	
	@RequestMapping(value="/getOrganization",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getOrganization(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		List<Organization> list = organizationService.getOrgan(cookies);
		
		return JSONArray.fromObject(list).toString();
	}
}

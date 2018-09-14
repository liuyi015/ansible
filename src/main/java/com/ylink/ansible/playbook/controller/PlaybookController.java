package com.ylink.ansible.playbook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylink.ansible.playbook.pojo.Parameter;
import com.ylink.ansible.playbook.pojo.Playbook;
import com.ylink.ansible.playbook.service.PlaybookService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/playbook")
@Controller
public class PlaybookController {
	
	@Autowired
	PlaybookService palybookService;

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "playbook/toAdd";
	}
	/**
	 * 获取playbook模板目录
	 * @return
	 */
	@RequestMapping("/getPBPackage")
	@ResponseBody
	public String getPBPackage() {
		List rs=palybookService.getPBTemp(null);
		return JSONArray.fromObject(rs).toString();
	}
	/**
	 * 获取具体playbook模板问价夹地址
	 */
	@RequestMapping("/getPBTemp")
	@ResponseBody
	public String getPBTemp(String uri) {
		List rs=palybookService.getPBTemp(uri);
		return JSONArray.fromObject(rs).toString();
	}
	/**
	 * 读取变量文件，获取变量信息
	 * @param uri
	 * @param request
	 * @return
	 */
	@RequestMapping("/readFile")
	public String readFile(String uri,HttpServletRequest request) {
		
		if(StringUtils.isNotEmpty(uri)) {
			List<Parameter> list=palybookService.readFile(uri, request);
			request.setAttribute("list", list);
		}
		return "playbook/playbook";
	}
	
	@RequestMapping("/checkProjectName")
	@ResponseBody
	public String checkProjectName(String projectName) {
		if(StringUtils.isEmpty(projectName)) {
			return JSONObject.fromObject("false").toString();
		}
		Boolean rs=palybookService.checkProjectName(projectName);
		return JSONArray.fromObject(rs).toString();
	}
	
	@RequestMapping("/doAdd")
	public String addPlaybook(Playbook playbook,HttpServletRequest request) {
		Boolean rs = palybookService.addPlaybook(playbook,request);
		if(rs) {
			request.getSession().setAttribute("msg", "新建成功！！！");
		}else {
			request.getSession().setAttribute("msg", "新建失败！！！");
		}
		return "redirect:/playbook/toAdd";
		
	}
	
}

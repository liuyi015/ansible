package com.ylink.ansible.playbook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "playbook/add";
	}
	
	@RequestMapping("/getPBPackage")
	@ResponseBody
	public String getPBPackage() {
		List rs=palybookService.getPBTemp(null);
		return JSONArray.fromObject(rs).toString();
	}
	
	@RequestMapping("/getPBTemp")
	@ResponseBody
	public String getPBTemp(String uri) {
		List rs=palybookService.getPBTemp(uri);
		return JSONArray.fromObject(rs).toString();
	}
	
	@RequestMapping("/doAdd")
	public String addPlaybook(Playbook playbook,HttpServletRequest request) {
		palybookService.addPlaybook(playbook,request);
		return "redirect:/playbook/toAdd";
		
	}
}

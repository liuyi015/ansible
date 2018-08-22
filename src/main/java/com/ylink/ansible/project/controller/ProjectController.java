package com.ylink.ansible.project.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.project.service.ProjectService;
import com.ylink.ansible.user.pojo.User;

@RequestMapping("/project")
@Controller
public class ProjectController {

	@Autowired
	public ProjectService projectService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String toList(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		List<Project> list = projectService.toList(cookies);
		request.setAttribute("list", list);
		return "project/list";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "project/add";
	}
	
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("project") Project project,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		//暂先设置默认值
		project.setScm_update_cache_timeout(0);
		project.setTimeout(0);
		
		String result = projectService.addProject(project,cookies);
		if(StringUtils.isEmpty(result)) {
			request.setAttribute("error", "add fail！！！！！");
		}
		request.setAttribute("success", "add success！！！！！");
		return "redirect:/project/list";
	}
	
	@RequestMapping("/toEdit/{id}")
	public String toEdit(@PathVariable(value="id") Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		Project project = projectService.findById(id,cookies);
		request.setAttribute("project", project);
		
		return "project/edit";
	}
}

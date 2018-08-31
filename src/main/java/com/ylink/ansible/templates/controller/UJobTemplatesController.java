package com.ylink.ansible.templates.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylink.ansible.job.pojo.Job;
import com.ylink.ansible.job.pojo.RunResult;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.templates.pojo.Template;
import com.ylink.ansible.templates.service.UJobTemplateService;

@RequestMapping("templates")
@Controller
public class UJobTemplatesController {
	@Autowired
	UJobTemplateService templateService;
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		List<Template> list = templateService.toList(cookies);
		request.setAttribute("list", list);
		return "template/list";
	}
	
	/**
	 * (do-search)根据条件查询project
	 * @param project
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String findBySearch(@ModelAttribute("template") Template template,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		Map<String,Object> params=new HashMap<>();
		if(template!=null) {
			String name = template.getName();
			if(StringUtils.isNotEmpty(name)) {
				params.put("name", name);
			}
			
		}
		//排序
		params.put("order_by", "name");
		List<Template> list = templateService.findTemplate(params,cookies);
		request.setAttribute("list", list);
		return "template/list";
	}
	
	@RequestMapping("/toAddJobTemp")
	public String toAtoAddJobTempdd() throws Exception {
		return "template/addJobTemp";
	}
	
	@RequestMapping(value="/doAddJobTemplate",method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("template") Template template,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		String result = templateService.addTemplate(template,cookies);
		if(StringUtils.isEmpty(result)) {
			request.getSession().setAttribute("msg", "add fail！！！！！");
			return "redirect:/templates/toAddJobTemp";
		}
		request.getSession().setAttribute("success", "add success！！！！！");
		return "redirect:/templates/list";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(@RequestParam(value="id") Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		Template template = templateService.findById(id,cookies);
		request.setAttribute("template", template);
		
		return "template/edit";
	}
	
	/**
	 * 根据ID编辑template
	 * @param template
	 * @param request
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEdit")
	public String doEdit(Template template,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		
		Cookie[] cookies = request.getCookies();
		//更新整个project
		String rs = templateService.UpdateTemplate(template, cookies);
		
		if(rs==null) {
			request.getSession().setAttribute("msg", "更新失败 ！！！！！ ");
		}else{
			request.getSession().setAttribute("msg", "更新成功 ！！！！！ ");
		}
		
		//重定向
		attr.addAttribute("id", template.getId());
		return "redirect:/templates/toEdit";
	}
	
	/**
	 * 根据ID删除templates
	 * @param id
	 * @param request
	 * @return  重定向到list页面
	 * @throws Exception
	 */
	@RequestMapping("/toDelete")
	public String toDelete(@RequestParam String id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		String rs = templateService.deleteTemplate(id,cookies);
		if(rs==null) {
			request.getSession().setAttribute("msg", "删除失败 ！！！！！");
		}else {
			request.getSession().setAttribute("msg", "成功删除 ！！！！！");
		}
		return "redirect:/templates/list";
	}

	@RequestMapping("/run")
	public String run(@RequestParam String id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		RunResult runResult = templateService.runTemplate(id,cookies);
		request.setAttribute("run", runResult);
		return "template/run";
	}
}

package com.ylink.ansible.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylink.ansible.common.ResultInfo;
import com.ylink.ansible.project.pojo.Project;
import com.ylink.ansible.project.service.ProjectService;
import com.ylink.ansible.user.pojo.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/project")
@Controller
public class ProjectController {

	@Autowired
	public ProjectService projectService;
	
	/**
	 * project列表查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String toList(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		String page = request.getParameter("page");
		if(StringUtils.isEmpty(page)) {
			page="1";
		}
		Map<String, Object> params=new HashMap<>();
		params.put("page", page);
		ResultInfo reInfo = projectService.toList(params, cookies);
		reInfo.setCurrentPage(Integer.parseInt(page));
		
		request.setAttribute("reInfo", reInfo);
		return "project/list";
	}
	
	/**
	 * 查询所有的project（返回json）
	 * @param request
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/allProject",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String allProject(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
//		List<Project> list = projectService.toList(cookies);
		List<Project> list = projectService.getAllProject(cookies);
		JSONArray arr = JSONArray.fromObject(list);
		return arr.toString();
	}
	/**
	 * 根据project 的id查找project 对应的playbook
	 * @param request
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/getPlaybook",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getPlaybook(Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		String playbook = projectService.getPlaybook(id,cookies);
		JSONArray arr = JSONArray.fromObject(playbook);
		return arr.toString();
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() throws Exception {
		return "project/add";
	}
	
	/**
	 * (do-ADD)新增project
	 * @param project
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("project") Project project,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		//暂先设置默认值
		project.setScm_update_cache_timeout(0);
		project.setTimeout(0);
		
		String result = projectService.addProject(project,cookies);
		if(StringUtils.isEmpty(result)) {
			request.getSession().setAttribute("msg", "add fail！！！！！");
			return "redirect:/project/toAdd";
		}else {
			request.getSession().setAttribute("msg", "add success！！！！！");
			return "redirect:/project/list";
		}
		
	}
	/**
	 * (do-search)根据条件查询project
	 * @param project
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String findBySearch(@ModelAttribute("project") Project project,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Map<String,Object> params=new HashMap<>();
		if(project!=null) {
			String scm_type = project.getScm_type();
			String name = project.getName();
			
			if(StringUtils.isNotEmpty(name)) {
				params.put("name", name);
			}
			if(scm_type!=null) {
				params.put("scm_type",scm_type);
			}
		}
		//排序
		params.put("order_by", "name");
		//List<Project> list = projectService.findProject(params,cookies);
		ResultInfo list = projectService.toList(params,cookies);
		request.setAttribute("list", list);
		return "project/list";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(@RequestParam(value="id") Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		Project project = projectService.findById(id,cookies);
		request.setAttribute("project", project);
		
		return "project/edit";
	}
	
	/**
	 * (do-Edit)根据ID编辑project
	 * @param project
	 * @param request
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEdit")
	public String doEdit(Project project,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		
		Cookie[] cookies = request.getCookies();
		//更新整个project
		Project pro = projectService.UpdateProject(project,cookies);
		
		if(pro==null) {
			request.getSession().setAttribute("msg", "更新失败 ！！！！！ ");
		}else{
			request.getSession().setAttribute("msg", "更新成功 ！！！！！ ");
		}
		
		//重定向
		attr.addAttribute("id", project.getId());
		return "redirect:/project/toEdit";
	}
	
	/**
	 * 根据ID删除project
	 * @param id
	 * @param request
	 * @return  重定向到list页面
	 * @throws Exception
	 */
	@RequestMapping("/toDelete")
	public String toDelete(@RequestParam String id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		String rs = projectService.deleteProject(id,cookies);
		if(rs==null) {
			request.getSession().setAttribute("msg", "删除失败 ！！！！！");
		}else {
			request.getSession().setAttribute("msg", "成功删除 ！！！！！");
		}
		return "redirect:/project/list";
	}
}

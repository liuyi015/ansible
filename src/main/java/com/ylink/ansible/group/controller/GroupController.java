package com.ylink.ansible.group.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylink.ansible.group.pojo.Group;
import com.ylink.ansible.group.service.GroupService;

import net.sf.json.JSONObject;

@RequestMapping("/group")
@Controller
public class GroupController {
	@Autowired
	private GroupService groupService;

	@RequestMapping("/toAdd")
	public String toAdd(Integer inventory,HttpServletRequest request) {
		Group group=new Group();
		group.setInventory(inventory);
		request.setAttribute("group", group);
		return "/group/add";
	}
	
	@RequestMapping("/doAdd")
	public String doAdd(Group group,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		String rs=groupService.addGroup(group,cookies);
		if(StringUtils.isEmpty(rs)) {
			request.getSession().setAttribute("msg", "add fail！！！！！");
			attr.addAttribute("inventory",group.getInventory());
			return "redirect:/group/toAdd";
		}else {
			request.getSession().setAttribute("msg", "add success！！！！！");
			attr.addAttribute("id",group.getInventory());
			return "redirect:/inventory/view";
		}
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Group group=groupService.findById(id,cookies);
		request.setAttribute("group", group);
		return "/group/edit";
	}
	
	@RequestMapping("/doEdit")
	public String doEdit(Group group,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		Group rs=groupService.updateGroup(group,cookies);
		if(rs!=null) {
			request.getSession().setAttribute("msg", "更新成功！！！！！！");
		}else {
			request.getSession().setAttribute("msg", "更新失败！！！！！！");
		}
		attr.addAttribute("id", group.getId());
		return "redirect:/group/toEdit";
	}
	
	@RequestMapping("/toDelete")
	public String toDelete(@RequestParam("id") Integer id,Integer inventory,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		String rs=groupService.deleteById(id, cookies);
		if(StringUtils.isNotEmpty(rs)) {
			request.getSession().setAttribute("msg", "删除成功！！！！！！");
		}else {
			request.getSession().setAttribute("msg", "删除失败！！！！！！");
		}
		attr.addAttribute("id",inventory);
		return "redirect:/inventory/view";
	}
}

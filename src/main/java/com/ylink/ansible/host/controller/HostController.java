package com.ylink.ansible.host.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ylink.ansible.host.pojo.Host;
import com.ylink.ansible.host.service.HostService;


@RequestMapping("/host")
@Controller
public class HostController {
	@Autowired
	private HostService hostService;

	@RequestMapping("/toAdd")
	public String toAdd(Integer inventory,HttpServletRequest request) {
		Host host=new Host();
		host.setInventory(inventory);
		request.setAttribute("host", host);
		return "/host/add";
	}
	
	@RequestMapping("/doAdd")
	public String doAdd(Host host,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		String rs=hostService.addHost(host,cookies);
		if(StringUtils.isEmpty(rs)) {
			request.getSession().setAttribute("msg", "add fail！！！！！");
			attr.addAttribute("inventory",host.getInventory());
			return "redirect:/host/toAdd";
		}else {
			request.getSession().setAttribute("msg", "add success！！！！！");
			attr.addAttribute("id",host.getInventory());
			return "redirect:/inventory/view";
		}
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Host host=hostService.findById(id,cookies);
		request.setAttribute("host", host);
		return "/host/edit";
	}
	
	@RequestMapping("/doEdit")
	public String doEdit(Host host,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		Host rs=hostService.updateGroup(host,cookies);
		if(rs!=null) {
			request.getSession().setAttribute("msg", "更新成功！！！！！！");
		}else {
			request.getSession().setAttribute("msg", "更新失败！！！！！！");
		}
		attr.addAttribute("id", host.getId());
		return "redirect:/host/toEdit";
	}
	
	@RequestMapping("/toDelete")
	public String toDelete(@RequestParam("id") Integer id,Integer inventory,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		String rs=hostService.deleteById(id, cookies);
		if(StringUtils.isNotEmpty(rs)) {
			request.getSession().setAttribute("msg", "删除成功！！！！！！");
		}else {
			request.getSession().setAttribute("msg", "删除失败！！！！！！");
		}
		attr.addAttribute("id",inventory);
		return "redirect:/inventory/view";
	}
}


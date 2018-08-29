package com.ylink.ansible.inventory.controller;

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

import com.ylink.ansible.inventory.pojo.Inventory;
import com.ylink.ansible.inventory.service.InventoryService;
import com.ylink.ansible.project.pojo.Project;

import net.sf.json.JSONArray;

@RequestMapping("/inventory")
@Controller
public class InventoryController {

	@Autowired
	public InventoryService inventoryService;

	/**
	 * 查询inventory（返回json）
	 * @param request
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/allInventory",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getAllInventory(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		List<Inventory> list = inventoryService.getAllInventory(cookies);
		JSONArray arr = JSONArray.fromObject(list);
		return arr.toString();
	}
	
	/**
	 * 查询inventory
	 * @param request
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String toList(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		List<Inventory> list = inventoryService.getAllInventory(cookies);
		request.setAttribute("list", list);
		return "inventory/list";
	}
	/**
	 * (do-search)根据条件查询project
	 * @param project
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String findBySearch(@ModelAttribute("inventory") Inventory inventory,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Map<String,Object> params=new HashMap<>();
		if(inventory!=null) {
			String name = inventory.getName();
			if(StringUtils.isNotEmpty(name)) {
				params.put("name", name);
			}
		}
		//排序
		params.put("order_by", "name");
		List<Inventory> list = inventoryService.findInventory(params,cookies);
		request.setAttribute("list", list);
		return "inventory/list";
	}
	/**
	 * (to-ADD)新增inventory
	 */
	@RequestMapping(value="/toAdd")
	public String toAdd() throws Exception {
		return "inventory/add";
	}
	
	/**
	 * (do-ADD)新增inventory
	 * @param project
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("inventory") Inventory inventory,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		
		String result = inventoryService.addInventory(inventory,cookies);
		if(StringUtils.isEmpty(result)) {
			request.getSession().setAttribute("msg", "add fail！！！！！");
			return "redirect:/inventory/toAdd";
		}else {
			request.getSession().setAttribute("msg", "add success！！！！！");
			return "redirect:/inventory/list";
		}
		
	}
	/**
	 * (to-Edit)编辑inventory
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toEdit")
	public String toEdit(@RequestParam("id") Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Inventory inventory=inventoryService.getInventoryById(id, cookies);
		request.setAttribute("inventory", inventory);
		return "/inventory/edit";
	}
	
	/**
	 * (do-Edit)编辑inventory
	 * @param inventory
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEdit")
	public String doEdit(@ModelAttribute("inventory") Inventory inventory,HttpServletRequest request,RedirectAttributes attr) throws Exception {
		Cookie[] cookies = request.getCookies();
		Inventory invent=inventoryService.updateInventory(inventory, cookies);
		if(invent!=null) {
			request.getSession().setAttribute("msg", "更新成功！！！！！！");
		}else {
			request.getSession().setAttribute("msg", "更新失败！！！！！！");
		}
		attr.addAttribute("id", inventory.getId());
		return "redirect:/inventory/toEdit";
	}
	
	@RequestMapping("/toDelete")
	public String toDelete(@RequestParam("id") Integer id,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		String rs=inventoryService.deleteById(id, cookies);
		if(StringUtils.isNotEmpty(rs)) {
			request.getSession().setAttribute("msg", "删除成功！！！！！！");
		}else {
			request.getSession().setAttribute("msg", "删除失败！！！！！！");
		}
		return "redirect:/inventory/list";
	}
	
}

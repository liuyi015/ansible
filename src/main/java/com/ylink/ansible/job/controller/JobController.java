package com.ylink.ansible.job.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ylink.ansible.common.ResultInfo;
import com.ylink.ansible.inventory.pojo.Inventory;
import com.ylink.ansible.job.service.JobService;

import net.sf.json.JSONObject;


@RequestMapping("/job")
@Controller
public class JobController {
	
	@Autowired
	JobService jobService;

	/*@RequestMapping(value="/list",method=RequestMethod.GET)
	public String toList(HttpServletRequest request) throws Exception {
		return findBySearch(null, null, request);
		
	}*/
	/**
	 * job列表（查询、翻页）
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String toList(HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Map<String, Object> params=new HashMap<>();
		ResultInfo resultInfo=new ResultInfo();
		
		String page = request.getParameter("page");
		if(StringUtils.isEmpty(page)) {
			page="1";
		}
		params.put("page", page);
		
		String search = request.getParameter("search");
		if(StringUtils.isNotEmpty(search)) {
			String[] s = search.split(" ");
			for(int i=0;i<s.length;i++) {
				params.put("search", s[i]);
			}
			request.setAttribute("search", search);
		}
		resultInfo=jobService.findUnifiedJob(params,cookies);
		if(resultInfo!=null) {
			resultInfo.setCurrentPage(Integer.parseInt(page));
		}
		request.setAttribute("reInfo", resultInfo);
		return "job/list";
	}
	
	@RequestMapping("/toDelete")
	@ResponseBody
	public String toDelete(@RequestParam String id,@RequestParam String type,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		if(StringUtils.isEmpty(type)&& StringUtils.isEmpty(id)) {
			request.getSession().setAttribute("msg", "找不到！！！！！！");
			return "redirect:/job/list";
		}
		String rs=jobService.deleteJob(id,type,cookies);
		if(StringUtils.isNotEmpty(rs)) {
			request.getSession().setAttribute("msg", "删除成功！！！！！！");
			return "2";
		}else {
			request.getSession().setAttribute("msg", "删除失败！！！！！！");
			return "1";
		}
//		return "redirect:/job/list";
		
	}
	
	@RequestMapping("/toView")
	public String toView(@RequestParam String id,@RequestParam String type,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		if(StringUtils.isEmpty(type)&& StringUtils.isEmpty(id)) {
			request.getSession().setAttribute("msg", "找不到！！！！！！");
			return "template/run";
		}
		Object rs=jobService.findByIdAndType(id,type,cookies);
		request.setAttribute("run", rs);
		return "template/run";
		
	}
	
	@RequestMapping("/getStatus")
	@ResponseBody
	public String getStatus(String url,HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		Object rs=jobService.findByUrl(url,cookies);
		request.setAttribute("run", rs);
		return JSONObject.fromObject(rs).toString();
		
	}
}

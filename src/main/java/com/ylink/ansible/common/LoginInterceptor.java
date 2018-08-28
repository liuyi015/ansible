package com.ylink.ansible.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ylink.ansible.user.pojo.User;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("-------------------LoginInterceptor-------------");
		
		String uri = request.getRequestURI();
		//不拦截登录的url
		if(uri.indexOf("login")>=0 || uri.indexOf("Login")>=0) {
			return true;
		}
		
		User user = (User) request.getSession().getAttribute("userSession");
		if(user==null) {
			 System.out.println("尚未登录，调到登录页面");
			 response.sendRedirect("/ansible/login");
			 request.getSession().setAttribute("loginMsg", "请先登录！");
			return false;
		}
		return true;
	}

}

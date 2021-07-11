package com.yrd.web.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
 * .登录验证过滤器
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//0.强制转换
		HttpServletRequest req = (HttpServletRequest) request;
		//1.获取资源的请求路径
		String uri = req.getRequestURI();
		//2.判断是否包含登录相关资源路径，要注意排除掉css/js/图片/验证码等资源
		if(uri.contains("/login.jsp")|| uri.contains("/loginServlet") 
				|| uri.contains("/css/") || uri.contains("/js/") 
				|| uri.contains("/fonts/")) {
			//包含，用户就是想登录。放行
			chain.doFilter(request, response);
		}else {
			//不包含，需要验证用户是否登录
			//3.从获取session中获取user
			Object user = req.getSession().getAttribute("user");
			if(user != null) {
				//登录了，放行
				chain.doFilter(request, response);
			}else {
				//没有登录。跳转登录页面
				req.setAttribute("login_msg", "您尚未登录，请登录");
				req.getRequestDispatcher("/login.jsp").forward(req, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}

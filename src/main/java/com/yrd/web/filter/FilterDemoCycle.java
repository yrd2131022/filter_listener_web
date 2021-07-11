package com.yrd.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * 1.定义一个类，实现接口Filter
 * 2.复写方法
 * 3.配置拦截路径
 * 		1.web.xml
 * 		2.注解
 */

//@WebFilter("/*")//访问所有资源之前，都会执行该过滤器
//@WebFilter("/user/*")
//@WebFilter("*.jsp")
@WebFilter("/index.jsp")//访问index.jsp之前，都会执行该过滤器
public class FilterDemoCycle implements Filter {

	/**
	 * .在服务器启动后，会创建Filter对象，然后调用init方法
	 * @Title:init
	 * @Description:
	 *
	 * @param filterConfig
	 * @throws ServletException
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 *
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("FilterDemoCycle init.........");
	}

	/**
	 * .每一次请求被拦截资源时，会执行。执行多次
	 * @Title:doFilter
	 * @Description:
	 *
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 *
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//对request对象请求消息增强		
		System.out.println("FilterDemoCycle --  doFilter  被执行了...");
		
		//放行
		chain.doFilter(request, response);
		 
		//对response对象的响应消息增强
		System.out.println("FilterDemoCycle --  doFilter  被执行了...回来了");
	}

	/**
	 * .在服务器关闭后，filter对象被销毁。如果服务器是正常关闭，则会执行destroy方法
	 * @Title:destroy
	 * @Description:
	 *
	 * @see javax.servlet.Filter#destroy()
	 *
	 */
	public void destroy() {
		System.out.println("FilterDemoCycle destroy...........");
	}

}

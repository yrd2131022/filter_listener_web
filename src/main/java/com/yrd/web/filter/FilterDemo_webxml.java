package com.yrd.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * .使用web.xml配置
 * 1.定义一个类，实现接口Filter
 * 2.复写方法
 * 3.配置拦截路径
 * 		1.web.xml
 * 		2.注解
 */


public class FilterDemo_webxml implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("使用web.xml配置 ,filterDemo1被执行了...");
		
		//放行
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

}

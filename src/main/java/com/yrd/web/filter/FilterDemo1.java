package com.yrd.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * 1.定义一个类，实现接口Filter
 * 2.复写方法
 * 3.配置拦截路径
 * 		1.web.xml
 * 		2.注解
 * 	<1>具体资源路径：/index.jsp  只有访问index.jsp资源时，过滤器才会被执行。
 * 	<2>拦截目录：/user/*   访问/user下的所有资源时，过滤器都会被执行
 * 	<3>后缀名拦截：*.jsp   访问所有jsp资源时，过滤器都会被执行
 * 	<4>拦截所有资源：/*       访问所有资源时，过滤器都会被执行
 * 4.拦截方式配置:资源被访问的方式
 * 		*注解配置
 * 			*设置dispatcherTypes属性
 * 				1.REQUEST:默认值。浏览器直接请求资源
 * 				2.FORWARD:转发访问资源
 * 				3.INCLUDE:包含访问资源
 * 				4.ERROR:错误跳转资源
 * 				5.ASYNC:异步访问资源
 * 		*web.xml配置
 * 			*设置<dispatcher></dispatcher>标签即可
 * 5.过滤器链（配置多个过滤器）
 * 		*执行顺序：如果有两个过滤器：过滤器1和过滤器2
 * 			过滤器：1-2-资源执行-2-1
 * 		*过滤器先后顺序：
 * 			1.注解配置：按照类名的字符串比较规则比较，值小的先执行
 * 				*如：AFilter 和 BFilter  , AFilter先执行
 * 			2.web.xml配置：<filter-maping>谁定义在上面，谁先执行
 */

//@WebFilter("/*")//访问所有资源之前，都会执行该过滤器
//@WebFilter("/demo.jsp")//访问demo.jsp之前，都会执行该过滤器
public class FilterDemo1 implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//对request对象请求消息增强		
		System.out.println("filterDemo1被执行了...");
		
		//放行
		chain.doFilter(request, response);
		 
		//对response对象的响应消息增强
		System.out.println("filterDemo1被执行了...回来了");
	}

	public void destroy() {
		
	}

}

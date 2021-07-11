package com.yrd.web.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * =====================================敏感词汇过滤=========================
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

@WebFilter("/*")//访问所有资源之前，都会执行该过滤器
public class SensitiveWordsFilter implements Filter {

	private List<String> list = new ArrayList<String>();//敏感词汇集合
	
	public void init(FilterConfig filterConfig) throws ServletException {
		BufferedReader br = null;
		try {
			//0.获取文件的真实路径
			ServletContext servletContext = filterConfig.getServletContext();
			String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
			//1.加载文件
			//2.读取文件
			br = new BufferedReader(new FileReader(realPath));
			//3.将文件的每一行数据添加到list中
			String line = null;
			while((line = br.readLine())!=null) {
				list.add(line.trim());
			}
			br.close();
			System.out.println(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SensitiveWordsFilter被执行了...");
		//1.创建代理对象，增强getParameter方法
		
		ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("method:"+method.getName());
				//增强getParameter方法
				//判断是否是getParameter方法
				if("getParameter".equals(method.getName())) {
					//增强返回值
					//获取返回值
					String value = (String) method.invoke(request, args);
					if(value != null) {
						for (String str : list) {
							if(value.contains(str)) {
								value = value.replaceAll(str,"***");
							}
						}
					}
					return value;
				}
				
				//判断方法名是否是 getParameterMap
				
				//判断方法名是否是 getParameterValue
				
				return method.invoke(request, args);
				
			}
		});
		
		System.out.println("SensitiveWordsFilter被执行了2...");
		
		//放行
		chain.doFilter(proxy_req, response);
		 
		//对response对象的响应消息增强
		System.out.println("SensitiveWordsFilter被执行了...回来了");
	}

	public void destroy() {
		
	}

}

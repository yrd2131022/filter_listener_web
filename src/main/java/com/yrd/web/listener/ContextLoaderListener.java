package com.yrd.web.listener;

import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * pei配置：1.xml配置2.注解配置
 * @ClassName:ContextLoaderListener
 * @Description:
 *
 * @author:Yrd
 * @date:2021-6-22 19:38:16
 *
 */

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	/**
	 * .监听ServletContext对象创建的。ServletContext对象服务器启动后自动创建。
	 * @Title:contextInitialized
	 * @Description:在服务器启动后自动创建
	 *
	 * @param sce
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 *
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
		//加载资源文件
		//1.获取ServletContext对象
		ServletContext servletContext = sce.getServletContext();
		
		//2.加载资源文件
		String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
		
		//3.获取真实路径
		String realPath = servletContext.getRealPath(contextConfigLocation);
		System.out.println("realPath:"+realPath);
		
		//4.加载进内存
		try {
			FileInputStream fis = new FileInputStream(realPath);
			System.out.println(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("ServletContext对象被创建了");
	}

	/**
	 * .在服务器关闭后，ServletContext对象被销毁。当服务器正常关闭后该方法被调用
	 * @Title:contextDestroyed
	 * @Description:
	 *
	 * @param sce
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 *
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext对象被销毁了");
	}

}

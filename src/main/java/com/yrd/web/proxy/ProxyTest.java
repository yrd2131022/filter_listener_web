package com.yrd.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * .动态代理:
 * 实现步骤：
 * 1.代理对象和真实对象实现相同的接口
 * 2.代理对象 = Proxy.newInstance();
 * 3.使用代理对象调用方法
 * 4.增强方法
 * 增强方式：
 * 1.增强参数列表
 * 2.增强返回值类型
 * 3.增强方法体执行逻辑
 */

public class ProxyTest {

	public static void main(String[] args) {
		// 1.创建真实对象
		Lenovo lenovo = new Lenovo();

		// 2.动态代理增强Lenovo对象
		/*
		 * 三个参数： 1.类加载器：真实对象 .getClass().getClassLoader() 2.接口数组：真实对象
		 * .getClass().getInterfaces() 3.处理器： new InvocationHandler()
		 */
		SaleComputer proxy_lenovo_object = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),
				lenovo.getClass().getInterfaces(), new InvocationHandler() {
					/*
					 * .代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法执行
					 * .参数：1.proxy:代理对象  2.method：代理对象调用的方法，被封装为的对象
					 * 		 3.args:代理对象调用的方法时，传递的实际参数
					 * 		 4.返回值Object,方法有返回值需要return 
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//						System.out.println(method.getName()+"方法被执行了。。。");
						
						//判断是否是sale方法
						if(method.getName().equals("sale")) {
							//1.增强参数
							double money = (double) args[0];
							money = money*0.85;
							System.out.println("买前，专车接你。。。");
							//使用真实对象调用该方法
							String object = (String) method.invoke(lenovo, money);
							System.out.println("买后，免费送货。。。");
							//2.增强返回值	
							return object+"_鼠标垫";
						}else {
							//使用真实对象调用该方法
							Object object = method.invoke(lenovo, args);
							return object;
						}
					}
				});

		// 3.调用方法
		String computer = proxy_lenovo_object.sale(8000);
		System.out.println(computer);
		proxy_lenovo_object.show();

	}

}

package com.dantefung.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestBoss {
	
	public static void main(String[] args) {
		//Human h = new SpringBrother(); 不能直接找
		
		final Human sb = new SpringBrother();
		//Human proxy = new SBProxy(sb);//找静态代理
		
		/*
		 * ClassLoader loader,  指定代理类运行的类加载器。固定写法：使用被代理类同一个类加载器
           Class<?>[] interfaces, 指定代理类实现的接口列表。固定写法：使用和被代理类同样的接口列表
           InvocationHandler h，代理类需要做什么事情，也就是代理策略。  策略设计模式。固定写法：传入InvocationHandler的实现类
		 * 
		 * */
		
		Human proxy = (Human)Proxy.newProxyInstance(
						sb.getClass().getClassLoader(), 
						sb.getClass().getInterfaces(),
						new InvocationHandler() {
							
							/*
							 * Object proxy, 代理类的实例
							 * Method method, 当前被调用的方法
							 * Object[] args 调用方法时传入的参数
							 * */
							@Override
							public Object invoke(Object proxy, Method method, Object[] args)
									throws Throwable {
								//策略
								
								//System.out.println("执行了");
								float money = (Float)args[0];
								
								if("sing".equals(method.getName())){
									sb.sing(money/2);
								}
								
								if("dance".equals(method.getName())){
									sb.sing(money/2);
								}
								
						
								return null;
							}
						} );//找动态代理
		
		proxy.sing(100);
		proxy.dance(50);
		
		ProxyUtil.generateClassFile(proxy.getClass(), "DynamicSBProxy");
	}
}

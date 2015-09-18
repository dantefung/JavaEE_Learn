package com.dantefung.proxy;
//被代理类。具体的实现
public class SpringBrother implements Human {

	@Override
	public void sing(float money) {
		System.out.println("收到了"+money+",然后开唱。。。。");
	}

	@Override
	public void dance(float money) {
		System.out.println("收到了"+money+"，然后开跳....");
	}

}

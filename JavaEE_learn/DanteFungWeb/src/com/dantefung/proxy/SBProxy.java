package com.dantefung.proxy;
//代理类，代理SpringBrother
public class SBProxy implements Human{
	private Human human;//找一个真实的会唱会跳的人
	
	public SBProxy(Human human){
		this.human = human;
	}

	@Override
	public void sing(float money) {
		human.sing(money);
	}

	@Override
	public void dance(float money) {
		human.dance(money);
	}

}

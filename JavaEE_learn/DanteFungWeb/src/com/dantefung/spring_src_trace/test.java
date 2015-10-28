package com.dantefung.spring_src_trace;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class test {
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/dantefung/spring_src_trace/bean.xml" );//读取bean.xml中的内容
        Person p = ctx.getBean("person",Person.class);//创建bean的引用对象
       System.out.println(p);
    }
}
package com.dantefung.c3p0_dbutils_basedao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//代表映射表的注解
//限制当前注解只能在类上使用
@Target({ElementType.TYPE})
//让该注解在运行时起作用
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String name();//表名
}

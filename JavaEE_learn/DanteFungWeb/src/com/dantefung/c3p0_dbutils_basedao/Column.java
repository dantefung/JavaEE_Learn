package com.dantefung.c3p0_dbutils_basedao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//代表字段的映射
//限制当前注解只能在字段上
@Target({ElementType.FIELD})
//让该注解在运行时起作用
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String name();// 表的字段名称
}

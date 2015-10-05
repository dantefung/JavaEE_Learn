package com.dantefung.hibernate_test;

import org.junit.Test;

import com.dantefung.utils.WebUtil;

public class CommonTest {
	
	@Test
	public void testWebUtil() {
		System.out.println(WebUtil.genUUID());
	}
}

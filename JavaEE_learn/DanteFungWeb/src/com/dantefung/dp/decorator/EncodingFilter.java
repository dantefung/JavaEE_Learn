package com.dantefung.dp.decorator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//请求和响应字符编码过滤器
public class EncodingFilter implements Filter {
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//响应输出字符编码问题
		response.setContentType("text/html;charset=utf-8");
		
		//解决Post
		request.setCharacterEncoding("utf-8");
		
		//增强HttpServletRequest的getParameter方法
		MyHttpServletRequest myRequest = new MyHttpServletRequest((HttpServletRequest)request);
		
		//放行: 放行的请求对象是装饰之后的请求对象
		chain.doFilter(myRequest, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}
	public void destroy() {

	}

}

/**
 * 1）编写一个装饰类，继承被装饰类 （被装饰类不能是final的）
	2）在装饰类中声明一个成员变量，类型是被装饰类的类型
	3）在装饰类的构造方法中，接收被装饰类的实例
	4）增强被装饰类的方法
 * @author APPle
 *
 */
class MyHttpServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		try {
			//得到原来的内容
			String value = request.getParameter(name);
			
			//只对GET方法提交的参数进行重新解码
			if("GET".equals(request.getMethod())){
				value = new String(value.getBytes("iso-8859-1"),"utf-8");
			}
			
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}








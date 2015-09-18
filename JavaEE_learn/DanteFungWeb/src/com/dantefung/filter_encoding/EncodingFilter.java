package com.dantefung.filter_encoding;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//  注意： 过滤器类一定要在web.xml中注册
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {	
		// 解决post编码问题
		request.setCharacterEncoding("utf-8");
		// 解决get请求的编码问题，为了该过滤器可以通用不可写死，
		// 采用装饰者模式来增强request的getParameter方法
		MyHttpServletRequest myRequest = new MyHttpServletRequest((HttpServletRequest)request);
		// 放行，让用户访问目标资源
		chain.doFilter(myRequest, response);
	}

/*查看servlet  api 可知：
 * 
 * javax.servlet.http 
	Interface HttpServletRequest
	All Superinterfaces: 
	ServletRequest 
	All Known Implementing Classes: 
	HttpServletRequestWrapper 
 */
class MyHttpServletRequest extends HttpServletRequestWrapper
{
	private HttpServletRequest request;
	
	public MyHttpServletRequest(HttpServletRequest request) 
	{
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) 
	{
		String result = "";

		if("GET".equals(request.getMethod()))
		{
			// 手动解码
			try
			{
				result = request.getParameter(name);
				result = new String(result.getBytes("iso-8859-1"),"utf-8");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			    throw new RuntimeException(e);
			}			
		}
		else  // post 方式请求
		{
			result = request.getParameter(name);
		}
		
		return result;
	}
	
}
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	


	@Override
	public void destroy() {

	}

}

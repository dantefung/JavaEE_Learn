package com.dantefung.dp.decorator;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
//网页内容压缩的过滤器
public class GZIPFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1)注意：请求不做任何过滤
		
		//思路： 装饰HttpServletResponse对象，增强getWriter方法，让其得到一个带有缓存功能的PrintWriter
		MyHttpServletResponse myResponse = new MyHttpServletResponse((HttpServletResponse)response);
				
		//直接放行
		chain.doFilter(request, myResponse);
		
		//3)执行响应的过滤任务： 对网页内容进行压缩
		
		//3.1 得到压缩前的内容？？？ 问题：response对象中没有得到响应正文的方法
		char[] result = myResponse.getContent();
		//System.out.println(result.length);
		
		//正在压缩
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(bas);
		gzip.write(new String(result).getBytes());
		gzip.finish();
		
		//得到压缩后的内容
		byte[] content = bas.toByteArray();
		
		//通知浏览器gzip格式内容
		myResponse.setHeader("content-encoding", "gzip");
		
		//输出给浏览器
		response.getOutputStream().write(content);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	public void destroy() {
	}
}


/**
 * 响应的装饰类
*/
class MyHttpServletResponse extends HttpServletResponseWrapper{
	private HttpServletResponse response;
	//缓存输出流
	private CharArrayWriter writer=new CharArrayWriter();
	//返回压缩前内容
	public char[] getContent(){
		return writer.toCharArray();
	}
	public MyHttpServletResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		//返回一个带缓存的PrintWriter
		return new PrintWriter(writer);
	}
}
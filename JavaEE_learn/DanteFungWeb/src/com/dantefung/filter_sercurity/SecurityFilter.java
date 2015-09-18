package com.dantefung.filter_sercurity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// 用户权限过滤
public class SecurityFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
			// 强转
		    HttpServletRequest req = (HttpServletRequest)request;
		    HttpServletResponse resp = (HttpServletResponse)response;
			
		    HttpSession session = req.getSession(false);
		    
		    // 如果session为null,意味访问者没有登录，非法访问其他页面
			if(session == null)
			{
				// 直接重定向到登录界面
				resp.sendRedirect( req.getContextPath()+ "/filter/login.jsp");
			}
			else// 如果存在session，说明访问者提交了登录请求，但是有可能后台程序会有错，因此也要如下的严谨的
			{
				if(session.getAttribute("userInfo") != null)
				{
					// 放行，访问目标资源
					chain.doFilter(request, response);
				}
			}
		
		
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}


	@Override
	public void destroy() {

	}

}

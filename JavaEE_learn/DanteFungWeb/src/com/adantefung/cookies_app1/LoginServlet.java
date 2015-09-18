package com.adantefung.cookies_app1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 模拟数据库的数据
		String username = "张三";
		String password = "123";
		// 从request域中取出的数据前，先解决乱码问题.
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String remember = request.getParameter("remember");
		
		// 判断用户名和密码是否正确
		response.setContentType("text/html;charset=utf-8");
		if(!username.equals(userName) || !password.equals(passWord))
		{
			response.getWriter().write("用户名或密码错误,2秒之后将跳回登录界面!!");
			response.setHeader("Refresh", "2;url='"+request.getContextPath()+"/LoginUIServlet'");
		}
		else
		{
			// 判断用户是否有勾选记住用户名和密码，如果有的话，给客户机会写cookie
			if("succ".equals(remember))
			{
				// cookie不能存中文数据，因此要对中文进行URL编码.
				String newUserName = URLEncoder.encode(username,"UTF-8");
				// 新建一个cookie对象
				/*
				 * 通过火狐的firebug可看到关于cookie的如下信息:
				 * 
				 *       名称                         内容                                                 域            原始大小                      路径                               过期时间 
				 *       name         value                domain   size          path           MaxAge
				 * username_password 张三-123             localhost   39B    /DanteFungWeb/    2083/09/12 下午11:06:01 
				 *                  %E5%BC%A0%E4%B8%89-123
				 *                      |-源数据(中文无法存在cookie中，要进行URL编码)
				 *  domain 域：主机的  域名  或者   ip地址
				 *  path 路径： web应用的根目录
				 *  MaxAge:
				 *         正数：表示cookie缓存在浏览器的缓存硬盘中,以秒为单位
				 *         0:表示删除cookie（注意：必须定位到唯一的cookie：name+domain+path）
				 *  
				 *  浏览器带cookie的规则是：
			     *                  当前访问路径.startWith(保存的cookie路径)   true 
                 *
			     *        "/day10_01_cookie".startWith("/day10_01_cookie/servlet")  false
			     *       "/day10_01_cookie/servlet".startWith("/day10_01_cookie")  true
                 *
		         *    记住这句话： 
		         *    如果浏览器保存的cookie放在应用的根目录下，
		         *    那么访问该应用下的所有资源都会带着这个cookie信息访问服务器。
		         *    （实际应用中这么做！！！）
				 */
				Cookie c = new Cookie("username_password",newUserName+"-"+password);
				// cookie的默认生命只能存留在浏览器的内存中，一个会话结束后，它就消亡了,因此要让其存活得更长点
				c.setMaxAge(Integer.MAX_VALUE);// 这个能让它活60多年
				// 将cookie的路径放在web应用的目录下
				c.setPath(request.getContextPath());
				// 加入response对象中,服务器会自动把放入的cookie发送给客户机.
				response.addCookie(c);
				PrintWriter out = response.getWriter();
				out.write("恭喜你登录成功！！<br/>根据您的选择，已经给你回写cookie数据<br/>下次登录不用再手动输入密码了Y^_^Y.");
				out.write("<a href='"+request.getContextPath()+"/LoginUIServlet?cookieName="+c.getName()+"'>点击这里，删除回写给你的cookie<br/></a>");
			}
			else
			{
				response.getWriter().write("恭喜你登录成功！！,根据您的选择，没有给你回写cookie数据");
			}
			
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

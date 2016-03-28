/**
 * Project Name:TestDemo
 * File Name:UserServlet.java
 * Package Name:com.dantefung.action
 * Date:2016-3-26上午11:05:12
 * Copyright (c) 2016, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.mail;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dantefung.utils.MD5Util;

/**
 * ClassName:UserServlet <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-3-26 上午11:05:12 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UserServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String op = request.getParameter("op");
		if("register".equals(op))
		{
			register(request, response);
		}
		else if("emailCheck".equals(op))
		{
			mailCheck(request, response);
		}
	}

	private void mailCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// 1、根据ID查询用户记录中的激活码
		// 2、比较激活码是否一致
		HttpSession session = request.getSession();
		String token = request.getParameter("token");
		String tem = (String)session.getAttribute("TOKEN");
		System.out.println(tem + "==" + token);
		if(tem.equals(token))
		{
			request.setAttribute("msg", "邮箱激活成功！请登录。");
		}
		else
		{
			request.setAttribute("msg", "邮箱激活失败！");
		}
		request.getRequestDispatcher("/mail/message.jsp").forward(request, response);
	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// 总体思路：
//		1.数据库加两个字，state字段（0:未激活，1：激活成功），token:(放激活码)
//		2.用户填写资料，插入数据成功，state字段默认是0,同时生成一个token也存入数据库
//		3.提示用户激活。。。发送邮件。。。邮件中带一个激活成功页的URL，URL里有两个参数（1，用户ID，2：激活码）
//		4.用户点击链接，回到激活成功页。。。激活成功页的Load事件，得到两个参数，以这两个参数为条件查询数据库里的数据，如果有，修改字段state为1,反之。。提示激活失败，重新激活。。
		
		// 获取用户注册信息
		String pwd = MD5Util.md5(request.getParameter("password"));
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(pwd);
		
		// 生成激活码的规则：用户邮箱 + 用户密码 + 当前时间 =>进行MD5加密=>激活码
		Calendar c = Calendar.getInstance();
		long time = c.getTimeInMillis();
		String token = MD5Util.md5(email+pwd+time);
		// 激活码过期时间
//		String token_exptime = (time + 1000*60*60*24) + "";
		String token_exptime = (time + 1000 + 20) + "";
		
		String id=UUID.randomUUID().toString().replace("-", "");
		// TODO 将用户信息存入数据库：insert into tb_user(id,username,pwd,token,token_exptime,regtime,status) values (?,?,?,?,?,sysdate,0)
		// 这里用session暂存token，模拟token存入数据库
		HttpSession session = request.getSession();
		session.setAttribute("TOKEN", token);
		
		// 准备邮件内容
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sb = new StringBuffer("<div style=\"width:660px;overflow:hidden;border-bottom:1px solid #bdbdbe;\"><div style=\"height:52px;overflow:hidden;border:1px solid #464c51;background:#353b3f url(http://www.lofter.com/rsc/img/email/hdbg.png);\"><a href=\"http://www.lofter.com?mail=qbclickbynoticemail_20120626_01\" target=\"_blank\" style=\"display:block;width:144px;height:34px;margin:10px 0 0 20px;overflow:hidden;text-indent:-2000px;background:url(http://i.imgur.com/uBv21zA.png) no-repeat;\">Dante's Blog</a></div>"+"<div style=\"padding:24px 20px;\">您好，"+email+"<br/><br/>Dante是一个\"专注兴趣、分享创作\"的人，旨在为\"热爱记录生活、追求时尚品质、崇尚自由空间\"的你，打造一个全新而定展示平台！<br/><br/>请点击下面链接激活账号，24小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
		sb.append("<a href=\"http://localhost:8080/TestDemo/userServlet?op=emailCheck&id=");
		sb.append(id);
        sb.append("&token=");
        sb.append(token);
        sb.append("\">http://localhost:8080/TestDemo/userServlet?op=emailCheck&id=");
        sb.append(id);
        sb.append("&token=");
        sb.append(token);
        sb.append("</a>"+"<br/>如果以上链接无法点击，请把上面网页地址复制到浏览器地址栏中打开<br/><br/><br/>Dante，专注兴趣，分享创作<br/>"+sdf.format(new Date())+ "</div></div>" );
        
        //发送邮件
	       /* SendEmail.send(email, sb.toString());*/
        SendMail sendMail = new SendMail(user);
        sendMail.setInfo(sb.toString());
        sendMail.start();
        request.setAttribute("msg", "恭喜您，注册成功，请到您的注册邮箱验证！"); 
        request.getRequestDispatcher("/mail/message.jsp").forward(request, response);
     
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}


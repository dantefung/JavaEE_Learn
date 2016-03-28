/**
 * Project Name:TestDemo
 * File Name:SendMail.java
 * Package Name:com.dantefung.mail
 * Date:2016-3-21下午3:18:21
 * Copyright (c) 2016, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * ClassName:SendMail <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-3-21 下午3:18:21 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SendMail extends Thread
{
	// 发件人地址
	private String from = "6637213hao@163.com";
	// 用户名
    private String username = "6637213hao@163.com";	
    // 密码
    private String password = "邮箱密码";
    // 邮件发送的服务器地址
    private String host = "smtp.163.com";

    private String info = null;
    
    private User user;
    
    public SendMail(User user)
	{
    	this.user = user;
	}
    
	@Override
	public void run()
	{
		try
		{
			// 1、准备一些基本的参数配置
			Properties prop = new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			// 2、设置参数配置,开启session
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			// 3、获取Transport并连接
			Transport ts = session.getTransport();
			ts.connect(host, username, password);
			// 4、创建Message
			Message message = createMail(session, user);
			// 5、发送Message
			ts.sendMessage(message, message.getAllRecipients());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}


	/**
	 * 
	 * createMail:创建要发送的邮件. <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author Dante Fung
	 * @param session
	 * @param user2
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	private Message createMail(Session session, User user) throws Exception
	{
		MimeMessage message = new MimeMessage(session);
		// 设置发件人
		message.setFrom(new InternetAddress(from));
		// 设置收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
		// 设置邮件主题
		message.setSubject("主题：用户注册邮件");
		
//		String info = "恭喜你，注册成功！您的用户名为：" + user.getUsername() + "，您的密码为：" + user.getPassword() + ",请妥善保管，如有问题请联系网站客服！";
		// 设置邮件正文
		message.setContent(getInfo(), "text/html;charset=UTF-8");
		// 保存邮件
		message.saveChanges();
		return  message;
	}
	

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}
	
	
}


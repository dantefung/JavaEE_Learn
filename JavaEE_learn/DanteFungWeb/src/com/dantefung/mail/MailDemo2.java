package com.dantefung.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//演示javamail发送带有html内容的邮件
public class MailDemo2 {

	public static void main(String[] args) throws Exception {
		/**
		 * 1）创建一次会话（邮件会话） 
			Session
		2）创建一封邮件,撰写邮件
			MimeMessage  
		3）发送邮件
			Transport

		 */
		
		//1)创建会话
		/**
		 * 参数一：属性参数      mail.host 服务器地址       mail.smtp.auth： 表示使用验证方式登录（base64）
		 * 参数二： 进行Base64编码后的数据
		 */
		Properties prop  = new Properties();
		prop.setProperty("mail.host", "smtp.126.com");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ericxu_12345@126.com","eric12345");
			}
			
		});
		//打开dubug
		session.setDebug(true);
		
		//2)创建一封邮件
		MimeMessage mail = new MimeMessage(session);
		
		//修改邮件
		
		//发件人
		mail.setFrom(new InternetAddress("ericxu_12345@126.com"));
		//收件人
		//参数一：收件类型        TO: 表示发送    CC: 抄送   BCC： 密送
			//    A   -> B(TO)
		    //    A   -> B(TO)  C(CC)
		   //     A   -> B(TO)  C(CC)  D(BC) 
			
		mail.setRecipient(RecipientType.TO, new InternetAddress("ericxu_12345@163.com"));
		
		//主题
		mail.setSubject("这是邮件的主题-第2封javamail邮件");
		
		//正文
		mail.setContent("<a href='http://gz.itcast.cn/'>传智播客官网</a><br/><img src='http://gz.itcast.cn/images/logo.gif'/><br/><font color='red' size='8'>今天我终于来到了心中向往已久的神圣学府--传智播客，开始了改变命运的征途</font>", "text/html;charset=utf-8");
		
		//3)发送邮件
		Transport.send(mail);
		
	}
		
		
}
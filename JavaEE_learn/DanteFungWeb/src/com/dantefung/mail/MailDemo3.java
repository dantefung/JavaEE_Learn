package com.dantefung.mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//演示javamail发送带有附件的邮件
public class MailDemo3 {

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
		mail.setSubject("这是邮件的主题-第5封javamail邮件");
		
		//创建附件
		File file = new File("C:/mm.jpg");
		MimeBodyPart bodyPart = new MimeBodyPart();
		bodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
		bodyPart.setFileName(file.getName());//附件名称
		
		//创建附件
		File file2 = new File("C:/dbassit.jar");
		MimeBodyPart bodyPart2 = new MimeBodyPart();
		bodyPart2.setDataHandler(new DataHandler(new FileDataSource(file2)));
		bodyPart2.setFileName(file2.getName());//附件名称
		
		
		
		//创建附件总类
		MimeMultipart mmp = new MimeMultipart();
		mmp.addBodyPart(bodyPart);
		mmp.addBodyPart(bodyPart2);
		
		//把所有附件放到邮件中
		mail.setContent(mmp);
		
		//3)发送邮件
		Transport.send(mail);
		
	}
		
		
}
package cn.test.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 * 邮件发送工具
 * @author RENSHUN
 *
 */

public class MailUtils {

	
	public static void sendMail(String to,String code){
		
		
		Properties props=new Properties();
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.store.protocol", "smtp");
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.auth", true);
		
		Session session=Session.getInstance(props, new Authenticator() {
			
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new javax.mail.PasswordAuthentication("refuela@163.com", "email password");
				
			}
		});
		
		Message message=new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("refuela@163.com"));
			
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("来自购物天堂商城官方激活邮件");
			
			message.setContent("<h1>购物天堂商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a "
					+ "href='http://192.168.1.109:8080/shop/user_active.do?"
					+ "code="+code+"'>http://192.168.1.109:8080/shop/user_active.do?"
					+ "code="+code+"</a></h3>", "text/html;charset=UTF-8");
			
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}

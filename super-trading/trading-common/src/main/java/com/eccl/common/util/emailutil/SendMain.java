package com.eccl.common.util.emailutil;

public class SendMain {

	/**
	 * @param args
	 *            * test send mail author by yangkun
	 */
	public static void main(String[] args) {

		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.sohu.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("dizhansuo@sohu.com");
		mailInfo.setPassword("123456");// 您的邮箱密码
		mailInfo.setFromAddress("dizhansuo@sohu.com");
		mailInfo.setToAddress("925853158@qq.com");
		mailInfo.setSubject("111111111111111111111");
		mailInfo.setContent("333333333333333333333");

		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		System.out.print("Y");
		// sms.sendHtmlMail(mailInfo);//发送html格式

	}

}

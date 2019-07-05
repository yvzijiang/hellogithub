/**
 * 
 */
package com.yvzijiang.github.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.yvzijiang.github.email.exception.AccountEmailException;

/**
 * @author yvzijiang
 *
 */
@Service
public class AccountEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${mail.systemEmail}")
	private String systemEmail;

	public void sendMail(String to, String subject, String htmlText) throws AccountEmailException {
		try {
			System.out.println(systemEmail);

			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg);

			msgHelper.setFrom("yuzijiang@outlook.com");
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText, true);

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			throw new AccountEmailException("Faild to send mail.", e);
		}
	}

	public void sendSimpleMail(String to, String subject, String text) throws AccountEmailException {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("yuzijiang@outlook.com");
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);

		javaMailSender.send(msg);
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

}

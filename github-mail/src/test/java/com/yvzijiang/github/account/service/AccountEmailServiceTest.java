package com.yvzijiang.github.account.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yvzijiang.github.email.exception.AccountEmailException;
import com.yvzijiang.github.email.service.AccountEmailService;

@SpringBootTest(classes = com.yvzijiang.github.email.Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration()
@PropertySource("classpath:/email.properties")
public class AccountEmailServiceTest {

	@Autowired
	private AccountEmailService service;

	public void testSendSimpleMail() {
		try {
			service.sendSimpleMail("407902312@qq.com", "简单邮件", "这是一封简单邮件");
		} catch (AccountEmailException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSendMail() {
		try {
			service.sendMail("407902312@qq.com", "测试邮件", "<html><body><b>这是一封测试邮件</b></body></html>");
		} catch (AccountEmailException e) {
			e.printStackTrace();
		}
	}

}

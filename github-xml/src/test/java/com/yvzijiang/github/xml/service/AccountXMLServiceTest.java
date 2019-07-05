/**
 * 
 */
package com.yvzijiang.github.xml.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yvzijiang.github.xml.dao.Account;

/**
 * @author yvzijiang
 *
 */
@SpringBootTest(classes = com.yvzijiang.github.xml.Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@PropertySource("classpath:/xml.properties")
public class AccountXMLServiceTest {

	@Autowired
	private AccountXMLService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		File XMLDataFile = new File("target/test-classes/xml-data.xml");

		if (XMLDataFile.exists()) {
			XMLDataFile.delete();
		}
		Account account = new Account();
		account.setId("yvzijiang");
		account.setName("YU Zijiang");
		account.setEmail("407902312@qq.com");
		account.setPassword("this_is_a_test");
		account.setActivated(true);

		service.createAccount(account);

	}

	/**
	 * Test method for
	 * {@link com.yvzijiang.github.xml.service.AccountXMLService#readAccount(java.lang.String)}.
	 */
	@Test
	public void testReadAccount() throws Exception {
		Account account = service.readAccount("yvzijiang");
		assertNotNull(account);
		assertEquals("yvzijiang", account.getId());
		assertEquals("YU Zijiang", account.getName());
	}

}

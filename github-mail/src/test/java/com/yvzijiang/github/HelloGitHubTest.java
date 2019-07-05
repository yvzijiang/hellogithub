/**
 * 
 */
package com.yvzijiang.github;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yvzijiang.github.email.HelloGitHub;

/**
 * @author yvzijiang
 *
 */
public class HelloGitHubTest {

	/**
	 * Test method for
	 * {@link com.yvzijiang.github.email.HelloGitHub#sayHello(java.lang.String)}.
	 */
	@Test
	public void testSayHello() {
		assertEquals("Hello GitHub!", new HelloGitHub().sayHello());
	}

}
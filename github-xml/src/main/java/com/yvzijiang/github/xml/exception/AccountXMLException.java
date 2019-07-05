package com.yvzijiang.github.xml.exception;

public class AccountXMLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -848305115643734668L;

	public AccountXMLException(String exceptionMsg, Exception e) {
		super(exceptionMsg, e);
	}
}

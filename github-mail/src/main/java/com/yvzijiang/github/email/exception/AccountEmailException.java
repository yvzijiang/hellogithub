package com.yvzijiang.github.email.exception;

public class AccountEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7690161661814771986L;

	public AccountEmailException(String exceptionMsg, Exception e) {
		super(exceptionMsg, e);
	}

}

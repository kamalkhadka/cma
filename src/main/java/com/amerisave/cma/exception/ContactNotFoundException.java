package com.amerisave.cma.exception;

public class ContactNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8847723204627430495L;

	public ContactNotFoundException(String exception) {
		super(exception);
	}

}
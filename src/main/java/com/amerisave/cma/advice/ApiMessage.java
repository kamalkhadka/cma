package com.amerisave.cma.advice;

import java.util.Date;

public class ApiMessage {
	private Date timestamp;
	private String message;

	public ApiMessage(Date timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

}

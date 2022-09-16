package com.chiran.powerplant.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timeStamp;
	private String message;

	public ExceptionResponse(Date timeStamp, String message) {
		this.timeStamp = timeStamp;
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

}
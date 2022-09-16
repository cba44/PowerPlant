package com.chiran.powerplant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BatteriesNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -1997145608988619719L;

	public BatteriesNotFoundException(String message) {
		super(message);
	}

}

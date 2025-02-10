package com.sea.probe.exception;

public class ProbeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String message;

	public ProbeException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

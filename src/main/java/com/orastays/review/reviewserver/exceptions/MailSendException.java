package com.orastays.review.reviewserver.exceptions;

public class MailSendException extends Exception {

	private static final long serialVersionUID = 7507768371105522281L;
	private String name;

	public MailSendException(String name) {
		super(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
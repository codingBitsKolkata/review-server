package com.orastays.review.reviewserver.exceptions;

public class EmptyValueException extends Exception {

	private static final long serialVersionUID = -7291401746312875166L;
	private String name;

	public EmptyValueException(String name) {
		super(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
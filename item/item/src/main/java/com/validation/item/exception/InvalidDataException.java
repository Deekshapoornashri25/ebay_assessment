package com.validation.item.exception;

/**
 * The Class InvalidDataException.
 */
public class InvalidDataException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4576671945236534685L;
	/** The message. */
	private Object details;


	public InvalidDataException(Object details) {
		super();
		this.details = details;
	}

	public Object getDetails() {
		return details;
	}


	public void setMessage(Object details) {
		this.details = details;
	}

}

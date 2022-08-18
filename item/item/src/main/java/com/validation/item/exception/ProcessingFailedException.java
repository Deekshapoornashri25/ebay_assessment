package com.validation.item.exception;

import org.springframework.http.HttpStatus;

/**
 * The Class JSONProcessingFailedException.
 */
public class ProcessingFailedException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2696522386138146115L;

    /** The message. */
    private String message;

    private HttpStatus httpStatus;

    /**
     * Instantiates a new JSON processing failed exception.
     *
     * @param message the message
     */
    public ProcessingFailedException(String message) {
        super();
        this.message = message;
    }

    public ProcessingFailedException(String message, HttpStatus httpStatus) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}

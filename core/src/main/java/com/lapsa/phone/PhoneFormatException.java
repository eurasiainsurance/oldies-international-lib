package com.lapsa.phone;

public class PhoneFormatException extends Exception {

    private static final long serialVersionUID = 1L;

    public PhoneFormatException(String message) {
	super(message);
    }

    public PhoneFormatException(String message, Throwable cause) {
	super(message, cause);
    }

}

package com.organization.leavemanagement.exception;

public class InvalidLeaveRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLeaveRequestException(String message) {
        super(message);
    }
}
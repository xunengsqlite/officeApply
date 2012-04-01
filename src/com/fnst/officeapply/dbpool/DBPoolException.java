package com.fnst.officeapply.dbpool;

public class DBPoolException extends Exception {

	private static final long serialVersionUID = -5953350107173800503L;

	public DBPoolException() {
	}

	public DBPoolException(String message) {
		super(message);
	}

	public DBPoolException(Throwable t) {
		super(t);
	}

	public DBPoolException(String message, Throwable t) {
		super(message, t);
	}
}

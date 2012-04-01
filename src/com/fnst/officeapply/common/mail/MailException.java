package com.fnst.officeapply.common.mail;


public class MailException extends Exception {
	
	private static final long serialVersionUID = 6442818142096646327L;

	/**
	 * コンストラクタ1
	 * @param message 異常メッセージ
	 * @param t 異常原因
	 */
	public MailException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * コンストラクタ2
	 * @param message 異常メッセージ
	 */
	public MailException(String message) {
		super(message);
	}

}

package com.fnst.officeapply.common.mail;


public class MailException extends Exception {
	
	private static final long serialVersionUID = 6442818142096646327L;

	/**
	 * �R���X�g���N�^1
	 * @param message �ُ탁�b�Z�[�W
	 * @param t �ُ팴��
	 */
	public MailException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * �R���X�g���N�^2
	 * @param message �ُ탁�b�Z�[�W
	 */
	public MailException(String message) {
		super(message);
	}

}

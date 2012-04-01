package com.fnst.officeapply.common.mail;

/**
 * ���[�����������߂̃N���X�ɂȂ�܂��B
 *
 */
public class Mail {
	
	/** ���M�� */
	private String from;

	/** ��M�ҁi�����j */
	private String[] to;

	/** �ʂ��ҁi�����j */
	private String[] cc;

	/** �Í��ʂ��ҁi�����j */
	private String[] bcc;

	/** ���[���̎��*/
	private String subject;

	/**���[���̓��e*/
	private String content;

	/**
	 * �R���X�g���N�^
	 * @param from���M��
	 * @param to��M�ҁi�����j
	 * @param cc�ʂ��ҁi�����j
	 * @param bcc�Í��ʂ��ҁi�����j
	 * @param subject���[���̎��
	 * @param content���[���̓��e
	 */
	public Mail(String from, String[] to, String[] cc, String[] bcc,
			String subject, String content) {
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.content = content;
	}

	
	public Mail() {
		super();
	}
	
	
	/**
	 * ���[�����M�҂��擾���܂��B
	 * @return ���M��
	 */
	public String getFrom() {
		return from;
	}
	
	
	/**
	 * ���[�����M�҂�ݒ肵�܂��B
	 * @param from ���[�����M��
	 */
	public void setFrom(String from) {
		this.from = from;
	}


	/**
	 * ���[����M�҂��擾���܂��B
	 * @return ��M��
	 */
	public String[] getTo() {
		return to;
	}

	
	/**
	 * ���[����M�҂�ݒ肵�܂��B
	 * @param to ���[����M��
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * ���[���ʂ��҂��擾���܂��B
	 * @return ���[���ʂ���
	 */
	public String[] getCc() {
		return cc;
	}
	
    /**
     * ���[���ʂ��҂�ݒ肵�܂��B
     * @param cc ���[���ʂ���
     */
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	
    /**
     * �Í��ʂ��҂��擾���܂��B
     * @return �Í��ʂ���
     */
	public String[] getBcc() {
		return bcc;
	}
	 
	/**
	 * �Í��ʂ��҂�ݒ肵�܂��B
	 * @param bcc �Í��ʂ���
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	
	
   /**
    * ���[�������擾���܂��B
    * @return ���[�����
    */
	public String getSubject() {
		return subject;
	}
	

	/**
	 * ���[������ݒ肵�܂��B
	 * @param subject ���[�����
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	

	/**
	 * ���[���̓��e���擾���܂��B
	 * @return ���[���̓��e
	 */
	public String getContent() {
		return content;
	}
	

	/**
	 * ���[���̓��e��ݒ肵�܂��B
	 * @param content ���[���̓��e
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

}

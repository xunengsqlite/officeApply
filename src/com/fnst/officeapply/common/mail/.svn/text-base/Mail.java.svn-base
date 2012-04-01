package com.fnst.officeapply.common.mail;

/**
 * メールを扱うためのクラスになります。
 *
 */
public class Mail {
	
	/** 送信者 */
	private String from;

	/** 受信者（複数） */
	private String[] to;

	/** 写し者（複数） */
	private String[] cc;

	/** 暗号写し者（複数） */
	private String[] bcc;

	/** メールの主題*/
	private String subject;

	/**メールの内容*/
	private String content;

	/**
	 * コンストラクタ
	 * @param from送信者
	 * @param to受信者（複数）
	 * @param cc写し者（複数）
	 * @param bcc暗号写し者（複数）
	 * @param subjectメールの主題
	 * @param contentメールの内容
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
	 * メール送信者を取得します。
	 * @return 送信者
	 */
	public String getFrom() {
		return from;
	}
	
	
	/**
	 * メール送信者を設定します。
	 * @param from メール送信者
	 */
	public void setFrom(String from) {
		this.from = from;
	}


	/**
	 * メール受信者を取得します。
	 * @return 受信者
	 */
	public String[] getTo() {
		return to;
	}

	
	/**
	 * メール受信者を設定します。
	 * @param to メール受信者
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * メール写し者を取得します。
	 * @return メール写し者
	 */
	public String[] getCc() {
		return cc;
	}
	
    /**
     * メール写し者を設定します。
     * @param cc メール写し者
     */
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	
    /**
     * 暗号写し者を取得します。
     * @return 暗号写し者
     */
	public String[] getBcc() {
		return bcc;
	}
	 
	/**
	 * 暗号写し者を設定します。
	 * @param bcc 暗号写し者
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	
	
   /**
    * メール主題を取得します。
    * @return メール主題
    */
	public String getSubject() {
		return subject;
	}
	

	/**
	 * メール主題を設定します。
	 * @param subject メール主題
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	

	/**
	 * メールの内容を取得します。
	 * @return メールの内容
	 */
	public String getContent() {
		return content;
	}
	

	/**
	 * メールの内容を設定します。
	 * @param content メールの内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

}

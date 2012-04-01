package com.fnst.officeapply.common.mail;

import java.util.ResourceBundle;

import com.fnst.officeapply.common.ComUtil;

/**
 * メール配置情報にアクセスするクラスになります。
 * 
 */
public class MailConfigInfo {

	/** メールサーバ */
	public static final String SERVER = "smtp.server";

	/** メールサーバ ポート */
	public static final String PORT = "port";
	
	/**メール発送プロトコル */
	public static final String TRANSPORT_PROTOCOL_NAME = "transport.protocol.name";

	/** メールのユーザ名 */
	public static final String USERNAME = "username";

	/** メールのユーザパスワード */
	public static final String PASSWORD = "password";

	/** メール認証フラグ */
	public static final String USEAUTHORIZATION = "authorization";

	/** メールの発送者 */
	public static final String MAILFROM = "mailfrom";

	/** メールのエンコーディング */
	public static final String MAIL_ENCODING = "mail.encoding";

	/** メールのテンプレートのパス */
	public static final String MAIL_TEMPLATE_PATH = "mail.template.path";

	/** メールプロパティファイル名 */
	public static final String COMMONMAIL = "CommonMail";

	/** メールテンプレートのキー のプリフィックス*/
	public static final String MAILKIND = "mail.kind.";

	/** メールテンプレートの値ー のプーストフィクス */
	public static final String VM = ".vm";

	/** メール主題のキーのプーストフィクス  */
	public static final String SUBJECT = ".subject";
	
	public static final String MAIL_CONFIG_FILE_SUFFIX = ".properties";

    /**
     * プロパティファイルへのアクセスオブジェクト
     */
	private static ResourceBundle rb = ResourceBundle.getBundle(COMMONMAIL);

	/**
	 * コンストラクタ
	 */
	private MailConfigInfo() {}

	
	/**
	 * 配置ファイルのキーで値を取得します。
	 * @param key  キー
	 * @return キーに対応する値
	 */
	public static String getResource(String key) {
		
		String result = "";

		if (!ComUtil.isBlank(key)) {
			try {
				result = rb.getString(key);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}

}

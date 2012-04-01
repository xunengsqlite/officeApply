package com.fnst.officeapply.common.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.fnst.officeapply.common.ComUtil;

/**
 * メールの発送を扱うクラスになります。
 */
public class MailSender {

	/**メールサーバ*/
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";

	/**メール発送プロトコル*/
	private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";

	/** メール認証フラグ*/
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	/**ポート*/
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";

	/**
	 * メールを発送します。
	 * @param to 受信者
	 * @param cc 写し者
	 * @param subject 主題
	 * @param mailBody 内容
	 * @throws MailException メールを発送中に使われる例外
	 */
	public static void sendEmail(String[] to, String[] cc, String subject,
			String mailBody) throws MailException{
		Mail mail = new Mail();
		mail.setFrom(MailConfigInfo.getResource(MailConfigInfo.MAILFROM));
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setContent(mailBody);
		mail.setCc(cc);
		sendEmail(mail);

	}

	/**
	 * メールを発送します。
	 * @param mail メールオブジェクト
	 * @throws MailException メールを発送中に使われる例外
	 */
	public static void sendEmail(Mail mail) throws MailException{
		try {
			if (mail == null) {
				throw new MailException("mail is null.");
			}
			Authenticator mailAuth = null;
			Session sendMailSession = null;
			
			String mailServer = MailConfigInfo.getResource(MailConfigInfo.SERVER);
			String isAuthChecked = MailConfigInfo.getResource(MailConfigInfo.USEAUTHORIZATION);
			String mailPort = MailConfigInfo.getResource(MailConfigInfo.PORT);
			String protocolName = MailConfigInfo.getResource(MailConfigInfo.TRANSPORT_PROTOCOL_NAME);
			String username = MailConfigInfo.getResource(MailConfigInfo.USERNAME);
			String password =  MailConfigInfo.getResource(MailConfigInfo.PASSWORD);
			String encoding = MailConfigInfo.getResource(MailConfigInfo.MAIL_ENCODING);
//			String 
			/*プロパティファイルから取得した値をpropsオブジェクトに設定します*/
			Properties props = new Properties();
			props.setProperty(MAIL_SMTP_HOST, mailServer);
			props.setProperty(MAIL_SMTP_PORT, mailPort);
			props.setProperty(MAIL_TRANSPORT_PROTOCOL, protocolName);

			/*メールを認証するかどうかをチェックします*/
			if (Boolean.parseBoolean(isAuthChecked)) {
				props.setProperty(MAIL_SMTP_AUTH, isAuthChecked);
				mailAuth = new MailAuth(username,password);
				sendMailSession = Session.getDefaultInstance(props, mailAuth);
			} else {
				sendMailSession = Session.getDefaultInstance(props);
			}

			MimeMessage message = new MimeMessage(sendMailSession);
			String subject = mail.getSubject();
			if(ComUtil.isBlank(subject)){
				throw new MailException("subject is blank");
			}
			//主題を設定します
			message.setSubject(subject, encoding);
			// 発信者のアドレスを設定します
			String mailfrom = mail.getFrom();
			if (ComUtil.isBlank(mailfrom)) {
				throw new MailException("sender is blank");
			}
			message.setFrom(new InternetAddress(mailfrom));

			// リストに受信者を追加します
			Address[] addressTo =getAddress(mail.getTo());

			// リストに写し受信者を追加します
			Address[] addressCc = getAddress(mail.getCc());
			//受信者が無しの場合、或いは、写し者が無しの場合、例外を投げる
			if ((addressTo == null ||addressTo.length == 0)&& (addressCc == null||addressCc.length==0)) {
				throw new MailException("The recipient of TO and CC are both blank");
			}
            //TOタイプでメール送信
			message.setRecipients(Message.RecipientType.TO, addressTo);
			//CCタイプでメール送信
			message.setRecipients(Message.RecipientType.CC, addressCc);
			String content = mail.getContent();
			if(ComUtil.isBlank(content)){
				throw new MailException("mail content is blank");
			}
			// メールのエンコーディングを設定します
			message.setText(content,encoding);
			
			message.setSentDate(new Date());
			//メールを送信します
			Transport.send(message);
		} catch (MessagingException e) {
			throw new MailException("Mail message error:",e);
		}

	}

	/**
	 * 受信者のアドレスを取得します
	 * @param addrstr メールアドレス
	 * @return 受信者のアドレス
	 * @throws AddressException アドレスを取得に失敗する場合、例外を投げる
	 * @throws MailException 
	 */
	private static Address[] getAddress(String[] addrstr) throws AddressException, MailException{
		
		List<Address> addressList = new ArrayList<Address>();
		Address [] address = null;
		if (addrstr != null) {
			for (int i = 0; i < addrstr.length; i++) {
				if (addrstr[i] != null && addrstr[i].trim().length() != 0){
					addressList.add(new InternetAddress(addrstr[i]));
				}
			}
			address = new Address[addressList.size()];
			address = addressList.toArray(address);
		}
		return address;
	}

}

/**
 *
 * メール認証を扱うクラスになります。
 */
class MailAuth extends Authenticator {

	String username;
	String password;

	/**
	 * コンストラクタ
	 * @param username ユーザ名
	 * @param password パスワード
	 */
	public MailAuth(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * パスワード認証オブジェクトをリターンします
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}

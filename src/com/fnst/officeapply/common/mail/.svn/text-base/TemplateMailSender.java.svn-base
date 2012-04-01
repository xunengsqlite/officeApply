package com.fnst.officeapply.common.mail;

import java.util.Map;

/**
 * テンプレートでメールの発信に扱うクラスになります。
 *
 */
public class TemplateMailSender {

	/**
	 * テンプレートでメールを発信します
	 * @param to  受信者
	 * @param cc  写し者
	 * @param template メールテンプレートのタイプ
	 * @param paramMap メール発送に必要な情報を格納する容器
	 * @throws MailException メールの発信に失敗する場合、例外を投げる
	 */
	public static void sendEmailByTemplate(int template, String[] to,
			String[] cc, Map<String, Object> paramMap) throws MailException {
		
		String subject = getSubject(template);
		String mailBody = getMailBody(template,paramMap);
		MailSender.sendEmail(to, cc, subject, mailBody);
		
	}


	/**
	 * メールテ主題を取得します
	 * @param template メールテンプレートのタイプ
	 * @return メールの主題
	 */
	private static String getSubject(int template) {

		return MailConfigInfo.getResource(MailConfigInfo.MAILKIND + template+ MailConfigInfo.SUBJECT);
	}

	/**
	 * メールの内容を取得します
	 * @param template メールテンプレートのタイプ
	 * @param paramMap メール発送に必要な情報を格納する容器
	 * @return メールの内容
	 * @throws MailException メールの発信に失敗する場合、例外を投げる
	 */
	public static String getMailBody(int template,Map<String,Object>paramMap) throws MailException {

		String templateName= MailConfigInfo.getResource(MailConfigInfo.MAILKIND + template + MailConfigInfo.VM);
		String mailBody = MailParserUtil.getInstance().parseVelocityTemplate(templateName, paramMap);
		return mailBody;
	}

}

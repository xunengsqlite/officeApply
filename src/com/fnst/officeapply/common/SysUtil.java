package com.fnst.officeapply.common;

public class SysUtil {
//	public static final int PAGE_SHOW_HISTORYNUM = 6;
//	private static String logFileDate = "";// 登录日志名称用时间标记唯一性
	// private static SimpleDateFormat sdf = null; 这样全局定义就要作同步

//	static {
//		logFileDate = getTimeStamp(new Date());
//	}

//	public static String formatDate(Date date) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		return sdf.format(date);
//
//	}

//	public static String getTimeStamp(Date date) { // 得到时间戳：yyyyMMddHHmmssSSS
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		return sdf.format(date);
//	}

	/**
	 * 同步是必须的，如果这是个写入大量信息的方法，那么一个线程在写时睡眠了，
	 * 另一个线程也开始写，就会出现问题，多用户并发登陆时问题严重。
	 */
//	public synchronized static void writeLogInfo(String logPath, Date date,
//			String logString, String standardDate) {
//		File dir = new File(logPath);
//		if (!dir.exists())
//			dir.mkdirs();
//		File file = new File(logPath + logFileDate + "-logInfo.txt");
//		if (file.length() > 1048576) {
//			file = new File(logPath + getTimeStamp(date) + "-logInfo.txt");
//			logFileDate = standardDate;
//		} // 如果文件大于1M，重新创建一个文件
//		FileOutputStream foo = null;
//		OutputStreamWriter osw = null;
//		try {
//			foo = new FileOutputStream(file, true);
//			osw =new OutputStreamWriter(foo,"utf-8");//排除文件乱码
//			osw.write(logString); // 写入日志字符串
//			osw.flush();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (foo != null) {
//					foo.close();
//				}
//				if (osw != null) {
//					osw.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	public static void recordLogInfo(String ip, String userRealName, String sign) {
//		// 得到登录信息日志文件夹
//		String logPath = InitParamServlet.logPath;
//		String logString = null;
//		Date date = new Date();
//		String standardDate = SysUtil.formatDate(date);
//		logString = sign + ":\tip:" + ip + "\tuserRealName:" + userRealName
//				+ "\tTIME:" + standardDate + "\n";
//		// 写入日志文件
//		writeLogInfo(logPath, date, logString, standardDate);
//	}

}

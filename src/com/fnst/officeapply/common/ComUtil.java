package com.fnst.officeapply.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.fnst.officeapply.dbpool.DBPoolManager;
import com.fnst.officeapply.framework.FrameworkConstant;
import com.fnst.officeapply.exception.OfficeException;

public class ComUtil {
	
	public static Logger logger = FrameworkConstant.logger;
	public static final String MD5_KEY = ":cookie@xuneng";
	public static final String ENCODING_BEFORE = "ISO-8859-1";
	public static final String ENCODING_AFTER = "UTF-8";
	
	public static boolean isEqual(Object obj1,Object obj2){
		if (obj1 == obj2){
			return true;
		}
		if (obj1 != null && obj1.equals(obj2)){
			return true;
		}
		if (obj2 != null && obj2.equals(obj1)){
			return true;
		}
		return false;
	}
	
	public static boolean isEqualsIgnoreCase(String obj1,String obj2){
		if (obj1 == obj2){
			return true;
		}
		if (obj1 != null && obj1.equalsIgnoreCase(obj2)){
			return true;
		}
		if (obj2 != null && obj2.equalsIgnoreCase(obj1)){
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(String str){
		if (str == null){
			return true;
		}
		if (str.length() == 0){
			return true;
		}
		
		return false;
	}
	
	public static boolean isEmpty(Object str){
		if (str == null){
			return true;
		}
		if (str instanceof String){
			return isEmpty((String)str);
		}
		return false;
	}
	
	public static boolean isBlank(String str){
		if (str == null){
			return true;
		}
		if (str.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	public static String changeNullVal(String value, String defVal){
		return (value == null) ? defVal : value;
	}
	
	public static boolean isExecSucc(int[] num) {
		if (num == null || num.length <= 0) {
			return false;
		}
		for (int i = 0; i < num.length; i++) {
			if (num[i] > 0) {
				return true;
			}
		}
		return false;
	}
	
	public static int getNextPageNum(int pageNum, int id, int amount, int singlePageCount){
		if (id == 1) {
			pageNum = 1;
		}else if (id == 2) {
			if (pageNum == 0) {
				if(amount > singlePageCount){
					pageNum = 2;
				}else{
					pageNum = 1;
				}
			}else if (amount > pageNum * singlePageCount) {
				pageNum = pageNum + 1;
			}
		} else if (id == 3) {
			if (pageNum == 0) {
				pageNum = 1;
			} else {
				if (pageNum > 1)
					pageNum = pageNum - 1;
			}
		} else if (id == 4) {
			if (amount % singlePageCount == 0) {
				pageNum = amount / singlePageCount;
			} else {
				pageNum = amount / singlePageCount + 1;
			}
		}
		return pageNum;
	}
	
	public static int getLastPageNum(int amount, int singlePageCount){
		int pageNum = 1;
		if (amount % singlePageCount == 0) {
			pageNum = amount / singlePageCount;
		} else {
			pageNum = amount / singlePageCount + 1;
		}
		if(pageNum <=0 ){
			pageNum = 1;
		}
		return pageNum;
	}
	
	public static int checkPageNum(int amount, int singlePageCount, int lastPageNum, int pageNum){
		if (pageNum * singlePageCount >= amount) {
			pageNum = lastPageNum;
			if(pageNum <= 0){
				pageNum =1;
			}
		}
		return pageNum;
	}
	
	public static String getThrowableStack(Throwable e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String errMsg = sw.toString();
		return errMsg;
	}
	
	public static int parseInt(String value, int defaultVal, String message, String poolName){
   	 int result;
   	 try {
            result = Integer.valueOf(value).intValue();
        } catch (NumberFormatException e) {
            DBPoolManager.logger.error( poolName+"连接池: " + message + value + ",已经设置成默认值："+defaultVal);
            result = defaultVal;
        }
        return result;
    }
	
	public final static String calcMD5(String value) {

		String s = (value == null ? "" : value); // 若为null返回空
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' }; // 字典
		try {
			byte[] strTemp = s.getBytes(); // 获取字节
			MessageDigest mdTemp = MessageDigest.getInstance("MD5"); // 获取MD5
			mdTemp.update(strTemp); // 更新数据
			byte[] md = mdTemp.digest(); // 加密
			int j = md.length; // 加密后的长度
			char str[] = new char[j * 2]; // 新字符串数组
			int k = 0; // 计数器k
			for (int i = 0; i < j; i++) { // 循环输出
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str); // 加密后字符串
		} catch (Exception e) {
			return null;
		}

	}
	
	public static String[] encodingVals(String[] values) throws OfficeException{
		if (values == null) {
			return values;
		}
		String[] encodingVals = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			String v = values[i];
			try {
				v = new String(v.getBytes(ENCODING_BEFORE), ENCODING_AFTER);
			} catch (UnsupportedEncodingException e) {
				throw new OfficeException("Encoding is not support: " + ENCODING_BEFORE + "　->　" + ENCODING_AFTER, e);
			}
			encodingVals[i] = v;
			logger.debug("requestValue：" + v);
		}
		return encodingVals;
	}
	
	public static String[] encodingVals(String[] values, String reqWay) throws OfficeException{
		if (values == null || isEqual(reqWay, "POST")) {
			return values;
		}
		String[] encodingVals = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			String v = values[i];
			try {
				v = new String(v.getBytes(ENCODING_BEFORE), ENCODING_AFTER);
			} catch (UnsupportedEncodingException e) {
				throw new OfficeException("Encoding is not support: " + ENCODING_BEFORE + "　->　" + ENCODING_AFTER, e);
			}
			encodingVals[i] = v;
			logger.debug("requestValue：" + v);
		}
		return encodingVals;
	}
	
	public static List<String> setParamTypes(String... s){
		List<String> types = new ArrayList<String>();
		if (s != null) {
			for (int i = 0; i < s.length; i++) {
				types.add(s[i]);
			}
		}
		return types;
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}

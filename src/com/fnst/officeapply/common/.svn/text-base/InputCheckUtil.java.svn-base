package com.fnst.officeapply.common;

public class InputCheckUtil {

	public static boolean checkGoods(String value){
		final String regex = "^[\\S]{1,255}$";
		if(ComUtil.isBlank(value)){
			return false;
		}
		return value.matches(regex);
	}
	
	public static boolean checkOrgzType(int type){
		if(type == 0 || type == 1 || type ==2){
			return true;
		}
		return false;
	}
	
	public static boolean checkOrgzName(String value) {
		final String regex = "^[\\S]{1,255}$";
		if(ComUtil.isBlank(value)){
			return false;
		}
		return value.matches(regex);
	}
	
	public static boolean isPositiveNumber(String str) {
		final String reg = "[1-9][0-9]*";
		if (ComUtil.isBlank(str)) {
			return false;
		}
		return str.matches(reg);
	}
	
	public static boolean checkNote(String str){
		final String reg = "[\\s\\S]{0,255}";//点号"."不匹配任意字符，无法匹配换行符等.
		if (str == null) {
			return false;
		}
		return str.matches(reg);
	}
	
	public static String replaceTags(String str){
		if(str == null){
			return str;
		}
		if (str.contains("<")) {
			return str.replace("<", "&lt;");
		}else if(str.contains(">")){
			return str.replace(">", "&gt;");
		}
		return str;
	}
	
	public static boolean isEmail(String str) {
		final String reg = "\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+";
		if (ComUtil.isBlank(str)) {
			return false;
		}
		return str.matches(reg);
	}
	
	public static boolean checkUsername(String str){
		final String regex = "^[a-zA-Z][a-zA-Z_0-9]{0,19}$";
		if(ComUtil.isBlank(str)){
			return false;
		}
		return str.matches(regex);
		
	}
	
	public static boolean checkPassword(String str){
		final String regex = "^[a-zA-Z0-9_]{1,20}$";
		if(ComUtil.isBlank(str)){
			return false;
		}
		return str.matches(regex);
	}
	
	public static boolean checkUserRealName(String str){
		final String regex = "^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$";
		if(ComUtil.isBlank(str)){
			return false;
		}
		return str.matches(regex);
	}
	
	public static boolean checkQueryTime(String str){
		final String regex = "\\d{4}-\\d{2}-\\d{2}";
		if(ComUtil.isBlank(str)){
			return false;
		}
		return str.matches(regex);
	}
	
}

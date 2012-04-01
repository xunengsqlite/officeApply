package com.fnst.officeapply.common;
import java.io.File;
import java.util.Date;

public class TestDemo {

	public static void main(String[] args) {
		System.out.println(byte[].class.getName());
		System.out.println(char[].class);
		System.out.println(int[].class.getName());
		System.out.println(long[].class.getName());
		System.out.println(double[].class);
		System.out.println(float[].class);
		System.out.println(boolean[].class);
		System.out.println(int.class.getName());
		System.out.println(Integer.class.getName());
		System.out.println(long.class);
		System.out.println(Long.class.getName());
		System.out.println(Long[].class.getName());
		System.out.println(Date[].class.getName());
		System.out.println(Byte.class);
		System.out.println(Byte[].class);
		System.out.println(Character.class);
		System.out.println(Character[].class);
		System.out.println(char [].class);
		System.out.println(Float[].class);
		System.out.println(Double[].class);
		System.out.println(boolean.class);
		System.out.println(Boolean[].class);
		System.out.println(short.class.getName());
		System.out.println(short[].class);
		System.out.println(Short.class);
		System.out.println(Short[].class);
		File f = new File("d:/dd/");
		System.out.println(f.isDirectory());
		String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
		String user_home = System.getProperty("user.home");
		System.out.println(WEB_ROOT);
		System.out.println(user_home);
		System.out.println(2.0>=2);
	}

}

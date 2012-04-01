package com.fnst.officeapply.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 对字符串进行压缩及加解密
 */
public class ZipBase64 {
	private static BASE64Encoder encoder = new BASE64Encoder();
	private static BASE64Decoder decoder = new BASE64Decoder();
	private static final int BUFFERSIZE = 8092;

	/**
	 * 先压缩后加密
	 */
	public static String encode(String str) {
		ByteArrayOutputStream byteos = new ByteArrayOutputStream();
		try {
			ByteArrayInputStream byteis = new ByteArrayInputStream(str.getBytes("UTF-8"));// 输入流
			ZipOutputStream zos = new ZipOutputStream(byteos);
			zos.setMethod(ZipOutputStream.DEFLATED);
			zos.putNextEntry(new ZipEntry("lbs"));
			int b = -1;
			byte buffer[] = new byte[BUFFERSIZE];
			while ((b = byteis.read(buffer)) != -1) {
				zos.write(buffer, 0, b);
			}
			zos.closeEntry();

		} catch (Exception e) {
			return null;
		}
		System.out.println("压缩后：" + new String(byteos.toByteArray()));
		
		// 从输出流获取String
		return encoder.encodeBuffer(byteos.toByteArray());
	}

	/**
	 * 先解密后解压
	 */
	public static String decode(String str){
		ByteArrayOutputStream jbyteos = new ByteArrayOutputStream();
		try {
			byte buffer1[] = new byte[BUFFERSIZE];
			ByteArrayInputStream jbyteis = new ByteArrayInputStream(decoder.decodeBuffer(str));
			ZipInputStream zis = new ZipInputStream(jbyteis);
			zis.getNextEntry();
			int b = -1;
			while ((b = zis.read(buffer1, 0, BUFFERSIZE)) != -1) {
				jbyteos.write(buffer1, 0, b);
			}

		} catch (Exception e) {
			return null;
		}
		return new String(jbyteos.toByteArray());
	}

	public static void main(String[] args) throws IOException {
		 String y="测试：123467890-=asdfghjkl;'zxcvbnm,./!@#$%^&*()_+:<>?";
//		String y = "xunengK";
		String j = ZipBase64.encode(y);
		String k = ZipBase64.decode(j);
		System.out.println("原数据:" + y);
		System.out.println("压缩并加密处理后:" + j);
		System.out.println("反处理:" + k);
	}

}

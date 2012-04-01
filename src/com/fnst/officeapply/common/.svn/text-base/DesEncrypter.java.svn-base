package com.fnst.officeapply.common;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.fnst.officeapply.exception.OfficeException;

public class DesEncrypter {
	
	public static final String KEY_1 = "made by xuneng!";

	public static final String KEY_2 = "I am in fnst!";
	
	public static byte[] encrypt(String content, String keyWord) throws OfficeException {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(keyWord.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			throw new OfficeException("encrypt failed:", e);
		}
	}
	
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	//加密接口
	public static String encrypttoStr(String content, String keyWord) throws OfficeException {
		return parseByte2HexStr(encrypt(content, keyWord));
	}

	//解密接口
	public static String decrypt(String content, String keyWord) throws OfficeException {
		return decrypt(parseHexStr2Byte(content), keyWord);
	}
	
	public static String decrypt(byte[] content, String keyWord) throws OfficeException {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(keyWord.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return new String(result);
		} catch (Exception e) {
			throw new OfficeException("encrypt failed:", e);
		}
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) throws OfficeException {
		String content = "1";
//		String Key = "made by xuneng!";

		// 加密
		System.out.println("加密前：" + content);
		String encryptResult = encrypttoStr(content, KEY_2);
		System.out.println("加密后：" + encryptResult);
		// 解密
		String decryptResult = decrypt(encryptResult, KEY_2);
		System.out.println("解密后：" + decryptResult);
	}
	
}

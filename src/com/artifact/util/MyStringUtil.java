package com.artifact.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MyStringUtil {
	/*
	 * 获取字符串的MD5加密算法
	 */
	public static String getMd5Encode(String inStr) {
		StringBuffer hexValue = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);

			hexValue = new StringBuffer();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return hexValue.toString();
	}

	/*
	 * 校正字符编码
	 */
	public static String changeCharset(String inStr, String oldCharset,
			String newCharset) {
		if (inStr == null) {
			return null;
		}
		try {
			return new String(inStr.getBytes(oldCharset), newCharset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 显示字符编码
	 */
	public static void showStringEncoding(String str) {
		String[] encode = new String[] { "GB2312", "ISO-8859-1", "UTF-8", "GBK" };
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(encode[i] + "~" + encode[j] + ":"
						+ changeCharset(str, encode[i], encode[j]));
			}
		}
	}

	/*
	 * 检查字符串编码格式
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/*
	 * 获取map数组降序排序
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String key1, String key2) {
						return key2.compareTo(key1);
					}
				});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}

	public static String getInvitationCode(int id) {
		return String.format("%06d", id);
	}
}

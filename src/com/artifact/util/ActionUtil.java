package com.artifact.util;

import java.math.BigInteger;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ActionUtil {
	/*
	 * 获取Cookie
	 */
	public static Cookie getCookie(String key, String value) {
		// 创建一个Cookie,包括(key,value).
		Cookie cookie = new Cookie(key, value);
		// 设置Cookie的生命周期,如果设置为负值的话,关闭浏览器就失效.
		cookie.setMaxAge(60 * 60 * 24 * 7);
		// 设置Cookie路径,不设置的话为当前路径(对于Servlet来说为request.getContextPath() +
		// web.xml里配置的该Servlet的url-pattern路径部分)
		cookie.setPath("/");
		// 输出Cookie
		return cookie;
	}

	/*
	 * 获取Url地址
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		baseUrl += request.getContextPath() + "/";
		return baseUrl;
	}

	/*
	 * 获取结束日期
	 */
	public static String getEndDate(String endDate) {
		String s[] = endDate.split("-");
		return s[0] + "-" + s[1] + "-" + (Integer.parseInt(s[2]) - 1);
	}

	/*
	 * 获取本月开始日期
	 */
	public static String getMonthStartDate() {
		Date date = new Date();
		return "" + (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + 1;
	}

	/*
	 * 获取当前日期
	 */
	public static int[] getDate() {
		int[] dateInt = new int[5];
		Date date = new Date();
		dateInt[0] = date.getYear() + 1900;
		dateInt[1] = date.getMonth() + 1;
		dateInt[2] = date.getDate();
		return dateInt;
	}

	/*
	 * 获取格式日期
	 */
	public static int[] getDate(Date date) {
		int[] dateInt = new int[5];
		dateInt[0] = date.getYear() + 1900;
		dateInt[1] = date.getMonth() + 1;
		dateInt[2] = date.getDate();
		return dateInt;
	}

	/*
	 * 获取本月结束日期
	 */
	public static String getMonthEndDate() {
		int[] mday = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		Date date = new Date();
		int y = (date.getYear() + 1900);
		if ((y % 40 == 0 && y % 100 != 0) || y % 400 == 0)// 判断是否是闰月
		{
			mday[1] = 29;
		}
		return "" + y + "-" + (date.getMonth() + 1) + "-" + mday[date.getMonth()];
	}

	/*
	 * 将积分转换为金额
	 */
	public static String getCoinsToMoney(int coins) {
		return String.format("%.2f", coins / 1000.00f);
	}

	/*
	 * 将金额转换为积分
	 */
	public static int getMoneyToCoins(float money) {
		return (int) (money * 1000);
	}

	/*
	 * 获取到期时间
	 */
	public static Date getExpirationDate(long date, int day) {
		BigInteger expirationTime = BigInteger.valueOf(day);
		expirationTime = expirationTime.multiply(BigInteger.valueOf(24 * 60 * 60 * 1000));
		expirationTime = expirationTime.add(BigInteger.valueOf(date));
		return new Date(expirationTime.longValue());
	}

	/*
	 * 获得客户端真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getDateFormatStringToInt(String buyTime) {
		return buyTime.split(" ", 2)[1].replace(":", "").replace(".", "");
	}
}

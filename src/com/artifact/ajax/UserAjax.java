package com.artifact.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.artifact.services.impl.UserAjaxServicesImpl;
import com.artifact.util.MyStringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserAjax extends ActionSupport
		implements ServletRequestAware, SessionAware, ApplicationAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response;
	private String userid;
	private String password;
	private String time;
	private String isLogin;
	private String sig;
	private String userInfo;
	private HashMap<String, Object> otherData;// 临时数据储存

	/*
	 * 登录
	 */
	public void login() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().login(userid, password, time, isLogin, sig);
		// System.out.println("用户" + userid + ",s登录代码：" + loginInfo);
		printString(loginInfo);
	}

	/*
	 * 注册用户
	 */
	public void registerUser() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().registerUser(userid, password, time, isLogin, sig,
				userInfo);
		printString(loginInfo);
	}

	/*
	 * 获取用户剩余时间
	 */
	public void getUserExpirationTime() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getUserExpirationTime(userid, password, time,
				isLogin, sig);
		printString(loginInfo);
	}

	/*
	 * 获取推广码
	 */
	public void getUserGeneralize() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getUserGeneralize(userid);
		printString(loginInfo);
	}

	/*
	 * 填写推广码
	 */
	public void addUserGeneralize() {
		boolean loginInfo = UserAjaxServicesImpl.getUserAjaxServices().addUserGeneralize(userid, userInfo);
		printString(loginInfo);
	}

	/*
	 * 获取用户推广列表 返回值：{用户id,推广时间,是否领奖,领取时间}
	 */
	public void getUserGeneralizes() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getUserGeneralizes(userid, password, time,
				isLogin, sig);
		printString(loginInfo);
	}

	/*
	 * 领取推广奖励
	 */
	public void updateIsGetRewards() {
		boolean loginInfo = UserAjaxServicesImpl.getUserAjaxServices().updateIsGetRewards(userid, password, time,
				isLogin, sig, userInfo);
		printString(loginInfo);
	}

	/*
	 * 获取用户是否为付费用户
	 */
	public void getIsBuyAssist() {
		boolean loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getIsBuyAssist(userid);
		printString(loginInfo);
	}

	/*
	 * 获取软件版本
	 */
	public void getSoftVersions() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getSoftVersions();
		// System.out.println("用户" + userid + ",s登录代码：" + loginInfo);
		printString(loginInfo);
	}

	/*
	 * 获取软件下载地址
	 */
	public void getSoftDownloadUrl() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getSoftDownloadUrl();
		printString(MyStringUtil.changeCharset(loginInfo, "UTF-8", "ISO-8859-1"));
	}

	/*
	 * 获取软件相关信息
	 */
	public void getSoftInfo() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getSoftInfo(userid);
		printString(MyStringUtil.changeCharset(loginInfo, "UTF-8", "ISO-8859-1"));
	}

	public void getSoftInfo2() {
		String loginInfo = UserAjaxServicesImpl.getUserAjaxServices().getSoftInfo2(userid);
		printString(MyStringUtil.changeCharset(loginInfo, "UTF-8", "ISO-8859-1"));
	}

	/*
	 * 向页面输出
	 */
	private void printString(Object str) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(str);
		out.close();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HashMap<String, Object> getOtherData() {
		return otherData;
	}

	public void setOtherData(HashMap<String, Object> otherData) {
		this.otherData = otherData;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}

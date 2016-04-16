package com.artifact.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.artifact.model.Admin;
import com.artifact.services.impl.IndexActionServicesImpl;
import com.artifact.util.ActionUtil;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements SessionAware,
		ApplicationAware, ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response;

	private String userAccount;
	private String userPassword;
	private String downloadUrl;

	@Override
	public String execute() throws Exception {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie key : cookies) {
				if ("userAccount".equals(key.getName())) {
					userAccount = key.getValue();
				}
				if ("userPassword".equals(key.getName())) {
					userPassword = key.getValue();
				}
			}
			if (userAccount != null) {
				Admin isLogin = IndexActionServicesImpl.getIndexActionServices()
						.login(userAccount, userPassword);
				if (isLogin != null) {
					session.put("userInfo", isLogin);
					if (isLogin.getUserType() == 0) {
						return "loginManager";
					}else if(isLogin.getUserType() == 1){
						return "agentManager";
					}
				}
			}
		}
		return SUCCESS;
	}

	/*
	 * 用户登录处理
	 */
	public String login() {
		Admin isLogin = IndexActionServicesImpl.getIndexActionServices().login(
				userAccount, userPassword);
		if (isLogin != null) {
			session.put("userInfo", isLogin);
			response.addCookie(ActionUtil.getCookie("userAccount",
					userAccount));
			response.addCookie(ActionUtil.getCookie("userPassword",
					userPassword));
			if (isLogin.getUserType() == 0) {
				return "loginManager";
			}else if(isLogin.getUserType() == 1){
				return "agentManager";
			}
		}
		request.setAttribute("msg", "账号或密码出错...");
		// request.put("msg", "账号或密码出错...");
		return SUCCESS;
	}

	/*
	 * 注销登录
	 */
	public String logout() {
		session.remove("userInfo");
		return SUCCESS;
	}
	
	/*
	 * 跳转下载链接
	 */
	public String getDownloadUrl() {
		userPassword = IndexActionServicesImpl.getIndexActionServices().getDownloadUrl(userAccount);
		return "downloadUrl";
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}

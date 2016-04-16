package com.artifact.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.artifact.services.impl.ManagerAjaxServicesImpl;
import com.artifact.util.MyStringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAjax extends ActionSupport
		implements RequestAware, SessionAware, ApplicationAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response;
	private String userid;
	private String info;// 变更信息
	private String remarksInfo;// 备注信息
	private int time;// 使用时间
	private int addNum;// 添加数
	private float renewMoney;//购买花费金额
	private HashMap<String, Object> otherData;// 临时数据储存

	/*
	 * 添加用户
	 */
	public void addUser() {
		String isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().addUser(time, info, remarksInfo);
		printString(MyStringUtil.changeCharset(isPay, "UTF-8", "ISO-8859-1"));
	}

	/*
	 * 批量添加用户
	 */
	public void batchAddUser() {
		String isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().batchAddUser(time, addNum, info, remarksInfo);
		printString(MyStringUtil.changeCharset(isPay, "UTF-8", "ISO-8859-1"));
	}

	/*
	 * 删除用户
	 */
	public void deleteUser() {
		boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().deleteUser(userid);
		printString(isPay);
	}

	/*
	 * 更新用户使用时间
	 */
	public void updateUsingTime() {
		boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().updateUsingTime(userid, time,renewMoney);
		printString(isPay);
	}

	/*
	 * 更新用户名
	 */
	public void updateUserId() {
		boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().updateUserid(userid, info);
		printString(isPay);
	}

	/*
	 * 更新用户密码
	 */
	public void updateUserPassword() {
		boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().updatePassword(userid, info);
		printString(isPay);
	}

	/*
	 * 更新用户信息
	 */
	public void updateUserInfo() {
		boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().updateUserInfo(userid, info);
		printString(isPay);
	}

	/*
	 * 更新备注信息
	 */
	public void updateRemarksInfo() {
		boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().updateRemarksInfo(userid, info);
		printString(isPay);
	}
	
	/*
	 * 删除付费信息
	 */
	public void deleteRenew() {
		boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().deleteRenew(userid);
		printString(isPay);
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

	public String getRemarksInfo() {
		return remarksInfo;
	}

	public void setRemarksInfo(String remarksInfo) {
		this.remarksInfo = remarksInfo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getAddNum() {
		return addNum;
	}

	public void setAddNum(int addNum) {
		this.addNum = addNum;
	}

	public float getRenewMoney() {
		return renewMoney;
	}

	public void setRenewMoney(float renewMoney) {
		this.renewMoney = renewMoney;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
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

}

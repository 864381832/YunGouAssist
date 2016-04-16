package com.artifact.model;

import java.util.Date;

/*
 * 用户表
 */
public class User {
	private int id;
	private String userid;// 用户id
	private String password;// 密码
	private Date registerTime;// 用户注册时间
	private Date expirationTime;// 到期时间
	private Date loginTime;// 最后一次登录时间
	private int loginNumber;// 登录次数
	private int userType;// 用户类型
	private int isOnLine;// 推广id
	private String phoneId;// 设备号
	private String userInfo;// 用户信息
	private String remarksInfo;// 备注信息
	private String renewNumber;// 续费次数

	public User() {
		super();
	}

	public User(int id, String userid, String password, Date registerTime, Date expirationTime, Date loginTime,
			int loginNumber, int userType, int isOnLine, String phoneId, String userInfo, String remarksInfo,
			String renewNumber) {
		super();
		this.id = id;
		this.userid = userid;
		this.password = password;
		this.registerTime = registerTime;
		this.expirationTime = expirationTime;
		this.loginTime = loginTime;
		this.loginNumber = loginNumber;
		this.userType = userType;
		this.isOnLine = isOnLine;
		this.phoneId = phoneId;
		this.userInfo = userInfo;
		this.remarksInfo = remarksInfo;
		this.renewNumber = renewNumber;
	}

	public User(String userid, String password, Date expirationTime, String phoneId) {
		super();
		this.userid = userid;
		this.password = password;
		this.expirationTime = expirationTime;
		this.phoneId = phoneId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public int getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(int loginNumber) {
		this.loginNumber = loginNumber;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public int getIsOnLine() {
		return isOnLine;
	}

	public void setIsOnLine(int isOnLine) {
		this.isOnLine = isOnLine;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getRemarksInfo() {
		return remarksInfo;
	}

	public void setRemarksInfo(String remarksInfo) {
		this.remarksInfo = remarksInfo;
	}

	public String getRenewNumber() {
		return renewNumber;
	}

	public void setRenewNumber(String renewNumber) {
		this.renewNumber = renewNumber;
	}

}

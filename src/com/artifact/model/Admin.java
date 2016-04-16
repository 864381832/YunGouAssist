package com.artifact.model;

import java.util.Date;

/*
 * 管理员表
 */
public class Admin {
	private int id;
	private String userid;// 用户id
	private String password;// 密码
	private Date loginTime;// 最后一次登录时间
	private int loginNumber;// 登录次数
	private int userType;// 用户类型
	private String remarksInfo;// 备注信息

	public Admin() {
		super();
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

	public String getRemarksInfo() {
		return remarksInfo;
	}

	public void setRemarksInfo(String remarksInfo) {
		this.remarksInfo = remarksInfo;
	}

}

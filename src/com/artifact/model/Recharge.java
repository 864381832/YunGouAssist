package com.artifact.model;

import java.util.Date;

/*
 * 充值卡
 */
public class Recharge {
	private int id;
	private String account;// 账号
	private String password;// 密码
	private int moneyValue;// 面值
	private int isUse;// 是否消费
	private Date useTime;// 消费时间
	private String userid;// 充值账号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMoneyValue() {
		return moneyValue;
	}

	public void setMoneyValue(int moneyValue) {
		this.moneyValue = moneyValue;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Recharge() {
		super();
	}
}

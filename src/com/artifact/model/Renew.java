package com.artifact.model;

import java.util.Date;

/*
 * 续费表
 */
public class Renew {
	private int id;
	private String userid;// 用户id
	private float renewMoney;// 续费金额
	private Date renewTime;// 续费时间
	private String userInfo;// 代理信息
	private int isDeal;// 是否处理
	private Date dealTime;// 处理时间

	public Renew() {
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

	public float getRenewMoney() {
		return renewMoney;
	}

	public void setRenewMoney(float renewMoney) {
		this.renewMoney = renewMoney;
	}

	public Date getRenewTime() {
		return renewTime;
	}

	public void setRenewTime(Date renewTime) {
		this.renewTime = renewTime;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public int getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(int isDeal) {
		this.isDeal = isDeal;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

}

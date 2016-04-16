package com.artifact.model;

import java.util.Date;

/*
 * 用户推广记录
 */
public class UserGeneralize {
	private int id;
	private String userid;// 用户id
	private String generalizeId;// 推广id
	private Date generalizeTime;// 推广注册时间
	private int isGetRewards;// 是否领取奖励
	private Date getRewardsTime;// 领取奖励时间

	public UserGeneralize() {
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

	public String getGeneralizeId() {
		return generalizeId;
	}

	public void setGeneralizeId(String generalizeId) {
		this.generalizeId = generalizeId;
	}

	public Date getGeneralizeTime() {
		return generalizeTime;
	}

	public void setGeneralizeTime(Date generalizeTime) {
		this.generalizeTime = generalizeTime;
	}

	public Date getGetRewardsTime() {
		return getRewardsTime;
	}

	public void setGetRewardsTime(Date getRewardsTime) {
		this.getRewardsTime = getRewardsTime;
	}

	public int getIsGetRewards() {
		return isGetRewards;
	}

	public void setIsGetRewards(int isGetRewards) {
		this.isGetRewards = isGetRewards;
	}

}

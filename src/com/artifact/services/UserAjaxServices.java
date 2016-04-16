package com.artifact.services;

import com.artifact.model.User;

public interface UserAjaxServices {
	public String login(String userid, String password, String time, String isLogin, String sig);// 用户登录

	public String getUserExpirationTime(String userid, String password, String time, String isLogin, String sig);// 获取用户剩余时间

	public String registerUser(String userid, String password, String time, String isLogin, String sig,
			String userInfo);// 用户注册

	public User getUser(String userid);// 获取用户信息

	public String getSoftVersions();// 获取软件版本

	public String getSoftDownloadUrl();// 获取软件下载地址

	public String getSoftInfo(String userid);// 获取软件相关信息

	public String getSoftInfo2(String userid);// 获取软件相关信息

	public boolean checkUser(String userid, String password, String time, String isLogin, String sig);// 验证用户

	public String getUserGeneralize(String userid);// 根据用户信息获取推广码

	public boolean addUserGeneralize(String userid, String userInfo);// 填写推广码

	public boolean updateIsGetRewards(String userid, String password, String time, String isLogin, String sig,
			String userInfo);// 领取推广奖励

	public String getUserGeneralizes(String userid, String password, String time, String isLogin, String sig);// 根据用户信息获取所以推广列表

	public boolean getIsBuyAssist(String userid);// 获取用户是否为付费用户
}

package com.artifact.services;

public interface ManagerAjaxServices {
	public boolean updateUsingTime(String userid, int time, float renewMoney);// 更新使用时间

	public String addUser(int time, String userInfo, String remarksInfo);// 添加用户

	public String batchAddUser(int time, int addNum, String userInfo, String remarksInfo);// 批量添加用户

	public boolean deleteUser(String userid);// 删除用户

	public boolean updateUserid(String userid, String userInfo);// 更新用名

	public boolean updatePassword(String userid, String userInfo);// 更新用户密码

	public boolean updateUserInfo(String userid, String userInfo);// 更新用户信息

	public boolean updateRemarksInfo(String userid, String remarksInfo);// 更新备注信息

	public boolean deleteRenew(String userid);// 删除用户
	
}

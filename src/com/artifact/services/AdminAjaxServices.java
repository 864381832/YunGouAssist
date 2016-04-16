package com.artifact.services;

public interface AdminAjaxServices {
	public boolean addAdmin(String userid, String password, String remarksInfo);// 添加用户

	public boolean deleteAdmin(String userid);// 删除用户

	public boolean updateAdminid(String userid, String AdminInfo);// 更新用名

	public boolean updatePassword(String userid, String AdminInfo);// 更新用户密码

	public boolean updateRemarksInfo(String userid, String remarksInfo);// 更新备注信息
	
	public boolean payMoney(String userid);//给用户打款
}

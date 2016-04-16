package com.artifact.dao;

import java.util.Date;
import java.util.List;

import com.artifact.model.Admin;

public interface AdminDao {
	public Admin addAdmin(String userid, String password, String remarksInfo);// 添加用户

	public Admin getAdmin(String userid, String password);

	public Admin getAdmin(String userid);

	public List<Admin> getAdmins();// 获取所以用户

	public int getAdminListNum();// 获取所有用户数

	public boolean AdminLogin(String userid, Date loginTime);// 用户登录

	public boolean deleteAdmin(String userid);// 删除用户

	public boolean updateUserid(String userid, String newUserid);//更新管理员账户

	public boolean updatePassword(String userid, String password);// 更新用户密码

	public boolean updateRemarksInfo(String userid, String remarksInfo);// 更新备注信息
}

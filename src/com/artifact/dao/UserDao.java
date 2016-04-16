package com.artifact.dao;

import java.util.Date;
import java.util.List;

import com.artifact.model.Renew;
import com.artifact.model.User;

public interface UserDao {
	public User addUser(String userid, String password, Date expirationTime, String userInfo, String remarksInfo);// 添加用户

	public User getUser(String userid, String password);

	public User getUser(String userid);

	public User getUser(int id);

	public List<User> getUsers();// 获取所以用户

	public List<User> getUsers(String userInfo);// 根据用户信息获取所以用户

	public List<User> fuzzyQueryUsers(String selectText);// 模糊查询用户

	public List<User> fuzzyQueryUsers(String selectText, String managerType);// 模糊查询代理商用户

	public int getUserListNum();// 获取所有用户数

	public int getUserListNum(String managerType);// 获取代理商所有用户数

	public List<User> getUserList(int firstPage, int pageSize, int sortType, boolean rankType);// 分页获取用户列表

	public List<User> getUserList(int firstPage, int pageSize, int sortType, boolean rankType, String managerType);// 分页获取代理商用户列表

	public boolean addUserUsingTime(String userid, int day, Date expirationTime);// 添加用户使用时间

	public boolean userLogin(String userid, Date loginTime);// 用户登录

	public boolean addUserPhoneId(String userid, String phoneId);// 清除用户绑定设备号

	public boolean deleteUser(String userid);// 删除用户

	public boolean updateUserid(String userid, String newUserid);// 更新用户名

	public boolean updatePassword(String userid, String password);// 更新用户密码

	public boolean updateUserInfo(String userid, String userInfo);// 更新用户信息

	public boolean updateRemarksInfo(String userid, String remarksInfo);// 更新备注信息

	public int getRenewExpirationUsersListNum();// 获取续费到期用户个数

	public List<User> getRenewExpirationUsers();// 获取续费到期用户

	public int getRenewFutureExpirationUsersListNum();// 获取续费即将到期用户个数

	public int getRenewNotExpirationUsersListNum();// 获取续费未到期用户个数

	public List<User> getRenewFutureExpirationUsers();// 获取续费即将到期用户

	public List<User> getRenewByDayList();// 分天获取用户列表
}

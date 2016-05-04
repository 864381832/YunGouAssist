package com.artifact.services.impl;

import java.util.Date;
import java.util.List;

import com.artifact.dao.RenewDao;
import com.artifact.dao.UserDao;
import com.artifact.dao.impl.RenewDaoImpl;
import com.artifact.dao.impl.UserDaoImpl;
import com.artifact.global.CacheSelectData;
import com.artifact.model.Renew;
import com.artifact.model.User;
import com.artifact.services.ManagerAjaxServices;
import com.artifact.util.ActionUtil;

public class ManagerAjaxServicesImpl implements ManagerAjaxServices {
	static private ManagerAjaxServices managerAjaxServices;
	private UserDao userDao = null;
	private RenewDao renewDao = null;

	public ManagerAjaxServicesImpl() {
		super();
		this.userDao = new UserDaoImpl();
		this.renewDao = new RenewDaoImpl();
	}

	public static ManagerAjaxServices getManagerAjaxServices() {
		if (managerAjaxServices == null) {
			managerAjaxServices = new ManagerAjaxServicesImpl();
		}
		return managerAjaxServices;
	}

	@Override
	public boolean updateUsingTime(String userid, int day, float renewMoney) {
		long nowTime = System.currentTimeMillis();
		User user = userDao.getUser(userid);
		if (nowTime < user.getExpirationTime().getTime()) {
			nowTime = user.getExpirationTime().getTime();
		}
		userDao.addUserUsingTime(userid, day, ActionUtil.getExpirationDate(nowTime, day));
		if (renewMoney > 0) {
			renewDao.addRenew(userid, renewMoney, user.getUserInfo());
		}
		return true;
	}

	@Override
	public String addUser(int day, String userInfo, String remarksInfo) {
		String userid = ("" + new Date().getTime()).substring(5);
		User user = userDao.getUser(userid);
		if (user == null) {
			long nowTime = System.currentTimeMillis();
			Date expirationDate = ActionUtil.getExpirationDate(nowTime, day);
			String password = String.format("%02d", ActionUtil.getDate(expirationDate)[1]);
			password += String.format("%02d", ActionUtil.getDate(expirationDate)[2]);
			userDao.addUser(userid, password, expirationDate, userInfo, remarksInfo);
			return "用户名：" + userid + "&nbsp;&nbsp;密码：" + password;
		} else {
			addUser(day, userInfo, remarksInfo);
		}
		return null;
	}

	@Override
	public boolean deleteUser(String userid) {
		CacheSelectData.removeUserCache(userid);
		return userDao.deleteUser(userid);
	}

	@Override
	public String batchAddUser(int time, int addNum, String userInfo, String remarksInfo) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < addNum; i++) {
			str.append(addUser(time, userInfo, remarksInfo));
			str.append("<br />");
		}
		return str.toString();
	}

	@Override
	public boolean updateUserInfo(String userid, String userInfo) {
		return userDao.updateUserInfo(userid, userInfo);
	}

	@Override
	public boolean updateRemarksInfo(String userid, String remarksInfo) {
		return userDao.updateRemarksInfo(userid, remarksInfo);
	}

	@Override
	public boolean updateUserid(String userid, String userInfo) {
		return userDao.updateUserid(userid, userInfo);
	}

	@Override
	public boolean updatePassword(String userid, String userInfo) {
		return userDao.updatePassword(userid, userInfo);
	}

	@Override
	public boolean deleteRenew(String userid) {
		return renewDao.deleteRenew(userid);
	}
}

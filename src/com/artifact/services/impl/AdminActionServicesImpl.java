package com.artifact.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.artifact.dao.AdminDao;
import com.artifact.dao.RenewDao;
import com.artifact.dao.UserDao;
import com.artifact.dao.impl.AdminDaoImpl;
import com.artifact.dao.impl.RenewDaoImpl;
import com.artifact.dao.impl.UserDaoImpl;
import com.artifact.global.config;
import com.artifact.model.Admin;
import com.artifact.model.User;
import com.artifact.services.AdminActionServices;
import com.artifact.services.ManagerActionServices;

public class AdminActionServicesImpl implements AdminActionServices {
	static private AdminActionServicesImpl adminActionServicesImpl;

	private UserDao userDao = null;
	private RenewDao renewDao = null;
	private AdminDao adminDao = null;

	public AdminActionServicesImpl() {
		super();
		this.userDao = new UserDaoImpl();
		this.renewDao = new RenewDaoImpl();
		this.adminDao = new AdminDaoImpl();
	}

	public static AdminActionServicesImpl getAdminActionServices() {
		if (adminActionServicesImpl == null) {
			adminActionServicesImpl = new AdminActionServicesImpl();
		}
		return adminActionServicesImpl;
	}

	@Override
	public int getAdminListNum() {
		return adminDao.getAdminListNum();
	}

	@Override
	public List<Map<String, Object>> getAdminList() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Admin> userList = adminDao.getAdmins();
		for (Admin user : userList) {
			if (user.getUserType() == 1) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Userid", user.getUserid());
				map.put("password", user.getPassword());
				map.put("loginTime", user.getLoginTime());
				map.put("loginNumber", user.getLoginNumber());
				map.put("remarksInfo", user.getRemarksInfo());
				int DrawingsRecordListSize = userDao.getUserListNum(user.getUserid());
				map.put("listSize", DrawingsRecordListSize);
				int RenewListNum = renewDao.getRenewListNum(user.getUserid());
				map.put("RenewListNum", RenewListNum);
				if (DrawingsRecordListSize == 0) {
					map.put("RenewScale", 0);
				}else {
					map.put("RenewScale", String.format("%.2f", (RenewListNum * 100.0f / DrawingsRecordListSize)));
				}
				int RenewMoney = renewDao.getRenewMoney(user.getUserid());
				int RenewNotPayMoney = renewDao.getRenewNotPayMoney(user.getUserid());
				map.put("RenewMoney", RenewMoney);
				map.put("RenewNotPayMoney", RenewNotPayMoney);
				map.put("RenewPayMoney", RenewMoney - RenewNotPayMoney);
				dataList.add(map);
			}
		}
		return dataList;
	}

	@Override
	public List<Map<String, Object>> getAdminList(String adminid) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Admin user = adminDao.getAdmin(adminid);
		if (user != null) {
			if (user.getUserType() == 1) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Userid", user.getUserid());
				map.put("password", user.getPassword());
				map.put("loginTime", user.getLoginTime());
				map.put("loginNumber", user.getLoginNumber());
				map.put("remarksInfo", user.getRemarksInfo());
				int DrawingsRecordListSize = renewDao.getRenewListNum(user.getUserid());
				map.put("listSize", DrawingsRecordListSize);
				int RenewListNum = renewDao.getRenewListNum(user.getUserid());
				map.put("RenewListNum", RenewListNum);
				if (DrawingsRecordListSize == 0) {
					map.put("RenewScale", 0);
				}else {
					map.put("RenewScale", String.format("%.2f", (RenewListNum * 100.0f / DrawingsRecordListSize)));
				}
				int RenewMoney = renewDao.getRenewMoney(user.getUserid());
				int RenewNotPayMoney = renewDao.getRenewNotPayMoney(user.getUserid());
				map.put("RenewMoney", RenewMoney);
				map.put("RenewNotPayMoney", RenewNotPayMoney);
				map.put("RenewPayMoney", RenewMoney - RenewNotPayMoney);
				dataList.add(map);
			}
		}
		return dataList;
	}

}

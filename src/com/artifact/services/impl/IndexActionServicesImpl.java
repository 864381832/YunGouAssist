package com.artifact.services.impl;

import java.util.Date;

import com.artifact.dao.AdminDao;
import com.artifact.dao.SoftInfoDao;
import com.artifact.dao.UserDao;
import com.artifact.dao.impl.AdminDaoImpl;
import com.artifact.dao.impl.SoftInfoDaoImpl;
import com.artifact.dao.impl.UserDaoImpl;
import com.artifact.model.Admin;
import com.artifact.services.IndexActionServices;

public class IndexActionServicesImpl implements IndexActionServices {
	private UserDao userDao;
	private AdminDao adminDao;
	private SoftInfoDao softInfoDao;

	public IndexActionServicesImpl() {
		userDao = new UserDaoImpl();
		adminDao = new AdminDaoImpl();
		softInfoDao = new SoftInfoDaoImpl();
	}

	static private IndexActionServices indexActionServices;

	public static IndexActionServices getIndexActionServices() {
		if (indexActionServices == null) {
			indexActionServices = new IndexActionServicesImpl();
		}
		return indexActionServices;
	}

	@Override
	public Admin login(String userAccount, String userPassword) {
		Admin user = adminDao.getAdmin(userAccount, userPassword);
		if (user != null) {
			adminDao.AdminLogin(userAccount, new Date());
		}
		return user;
	}

	public String getDownloadUrl(String name) {
		return softInfoDao.getSoftInfo(name).getValue();
	}
}

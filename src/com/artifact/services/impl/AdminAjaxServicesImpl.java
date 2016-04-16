package com.artifact.services.impl;

import com.artifact.dao.AdminDao;
import com.artifact.dao.RenewDao;
import com.artifact.dao.impl.AdminDaoImpl;
import com.artifact.dao.impl.RenewDaoImpl;
import com.artifact.services.AdminAjaxServices;

public class AdminAjaxServicesImpl implements AdminAjaxServices {
	static private AdminAjaxServicesImpl adminAjaxServicesImpl;

	private AdminDao adminDao = null;
	private RenewDao renewDao = null;

	public AdminAjaxServicesImpl() {
		super();
		this.adminDao = new AdminDaoImpl();
		this.renewDao = new RenewDaoImpl();
	}

	public static AdminAjaxServicesImpl getAdminAjaxServices() {
		if (adminAjaxServicesImpl == null) {
			adminAjaxServicesImpl = new AdminAjaxServicesImpl();
		}
		return adminAjaxServicesImpl;
	}

	@Override
	public boolean addAdmin(String userid, String password, String remarksInfo) {
		return adminDao.addAdmin(userid, password, remarksInfo) != null;
	}

	@Override
	public boolean deleteAdmin(String userid) {
		return adminDao.deleteAdmin(userid);
	}

	@Override
	public boolean updateAdminid(String userid, String AdminInfo) {
		return adminDao.updateUserid(userid, AdminInfo);
	}

	@Override
	public boolean updatePassword(String userid, String password) {
		return adminDao.updatePassword(userid, password);
	}

	@Override
	public boolean updateRemarksInfo(String userid, String remarksInfo) {
		return adminDao.updateRemarksInfo(userid, remarksInfo);
	}

	@Override
	public boolean payMoney(String userid) {
		return renewDao.setRenewIsDeal(userid);
	}

}

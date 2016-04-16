package com.artifact.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.artifact.dao.AdminDao;
import com.artifact.model.Admin;
import com.artifact.util.JdbcUtil;

public class AdminDaoImpl implements AdminDao {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public Admin addAdmin(String userid, String password, String remarksInfo) {
		String sql = "INSERT INTO `yungou_assist`.`admin` (`userid`, `password`,`remarksInfo`) VALUES (?,?,?)";
		try {
			return runner.insert(sql, new BeanHandler<Admin>(Admin.class), userid, password,remarksInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Admin getAdmin(String userid, String password) {
		String sql = "select * from admin where userid = ? and password = ?";
		try {
			return runner.query(sql, new BeanHandler<Admin>(Admin.class), userid, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Admin getAdmin(String userid) {
		String sql = "select * from admin where userid = ?";
		try {
			return runner.query(sql, new BeanHandler<Admin>(Admin.class), userid);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Admin> getAdmins() {
		String sql = "select * from admin";
		try {
			return runner.query(sql, new BeanListHandler<Admin>(Admin.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public int getAdminListNum() {
		String sql = "select count(id) as id from admin WHERE `userType`=1";
		try {
			return runner.query(sql, new BeanHandler<Admin>(Admin.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean AdminLogin(String userid, Date loginTime) {
		String sql = "UPDATE admin SET `loginNumber`=loginNumber+1,`loginTime`=? WHERE `userid`=?";
		try {
			return runner.update(sql, loginTime, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAdmin(String userid) {
		String sql = "DELETE FROM `admin` WHERE `userid`=?";
		try {
			return runner.update(sql, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateRemarksInfo(String userid, String remarksInfo) {
		String sql = "UPDATE admin SET `remarksInfo`=? WHERE `userid`=?";
		try {
			return runner.update(sql, remarksInfo, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserid(String userid, String newUserid) {
		String sql = "UPDATE admin SET `userid`=? WHERE `userid`=?";
		try {
			return runner.update(sql, newUserid, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePassword(String userid, String password) {
		String sql = "UPDATE admin SET `password`=? WHERE `userid`=?";
		try {
			return runner.update(sql, password, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

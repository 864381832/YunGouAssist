package com.artifact.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.artifact.dao.UserDao;
import com.artifact.model.User;
import com.artifact.util.JdbcUtil;

public class UserDaoImpl implements UserDao {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public User addUser(String userAccount, String password, Date expirationTime, String userInfo, String remarksInfo) {
		String sql = "insert into user(userid,password,expirationTime,userInfo,remarksInfo) values(?,?,?,?,?)";
		try {
			return runner.insert(sql, new BeanHandler<User>(User.class),
					new Object[] { userAccount, password, expirationTime, userInfo, remarksInfo });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUser(String userId) {
		String sql = "select * from user where userid = ?";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), userId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public User getUser(String userId, String password) {
		String sql = "select * from user where userid = ? and password = ?";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), new Object[] { userId, password });
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUser(int id) {
		String sql = "select * from user where id = ?";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		String sql = "select * from user";
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> fuzzyQueryUsers(String selectText) {
		String str = "%" + selectText + "%";
		String sql = "select * from user WHERE `userInfo` LIKE ? or `remarksInfo` LIKE ? or userid = ?";
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class), str, str, selectText);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> fuzzyQueryUsers(String selectText, String managerType) {
		String str = "%" + selectText + "%";
		String sql = "select * from user WHERE `userInfo` = ? and `remarksInfo` LIKE ? or userid = ?";
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class), managerType, str, selectText);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getUsers(String userInfo) {
		String sql = "select * from user WHERE `userInfo`=?";
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class), userInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public int getUserListNum(String managerType) {
		String sql = "select count(id) as id from user WHERE  `userInfo`=?";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), managerType).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getUserListNum() {
		String sql = "select count(id) as id from user";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> getUserList(int firstPage, int pageSize, int sortType, boolean rankType) {
		String sql = null;
		switch (sortType) {
		case 0:// 默认排序（注册时间排序）
			if (rankType) {
				sql = "select * from user order by registerTime desc limit ?,?";
			} else {
				sql = "select * from user order by registerTime asc limit ?,?";
			}
			break;
		case 1:// 按到期时间排序（剩余天数）
			if (rankType) {
				sql = "select * from user order by expirationTime desc limit ?,?";
			} else {
				sql = "select * from user order by expirationTime asc limit ?,?";
			}
			break;
		case 2:// 按登录次数排序
			if (rankType) {
				sql = "select * from user order by loginNumber desc limit ?,?";
			} else {
				sql = "select * from user order by loginNumber asc limit ?,?";
			}
			break;
		case 3:// 按最后登陆时间排序
			if (rankType) {
				sql = "select * from user order by loginTime desc limit ?,?";
			} else {
				sql = "select * from user order by loginTime asc limit ?,?";
			}
			break;
		case 4:// 按用户信息排序
			if (rankType) {
				sql = "select * from user order by userInfo desc limit ?,?";
			} else {
				sql = "select * from user order by userInfo asc limit ?,?";
			}
			break;
		case 5:// 按备注信息排序
			if (rankType) {
				sql = "select * from user order by remarksInfo desc limit ?,?";
			} else {
				sql = "select * from user order by remarksInfo asc limit ?,?";
			}
			break;
		default:
			break;
		}
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class), firstPage * pageSize, pageSize);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getUserList(int firstPage, int pageSize, int sortType, boolean rankType, String managerType) {
		String sql = null;
		switch (sortType) {
		case 0:// 默认排序（注册时间排序）
			if (rankType) {
				sql = "select * from user WHERE userInfo=? order by registerTime desc limit ?,?";
			} else {
				sql = "select * from user WHERE userInfo=? order by registerTime asc limit ?,?";
			}
			break;
		case 1:// 按到期时间排序（剩余天数）
			if (rankType) {
				sql = "select * from user WHERE userInfo=? order by expirationTime desc limit ?,?";
			} else {
				sql = "select * from user WHERE userInfo=? order by expirationTime asc limit ?,?";
			}
			break;
		case 2:// 按登录次数排序
			if (rankType) {
				sql = "select * from user WHERE userInfo=? order by loginNumber desc limit ?,?";
			} else {
				sql = "select * from user WHERE userInfo=? order by loginNumber asc limit ?,?";
			}
			break;
		case 3:// 按最后登陆时间排序
			if (rankType) {
				sql = "select * from user WHERE userInfo=? order by loginTime desc limit ?,?";
			} else {
				sql = "select * from user WHERE userInfo=? order by loginTime asc limit ?,?";
			}
			break;
		case 4:// 按用户信息排序
			if (rankType) {
				sql = "select * from user WHERE userInfo=? order by userInfo desc limit ?,?";
			} else {
				sql = "select * from user WHERE userInfo=? order by userInfo asc limit ?,?";
			}
			break;
		case 5:// 按备注信息排序
			if (rankType) {
				sql = "select * from user WHERE userInfo=? order by remarksInfo desc limit ?,?";
			} else {
				sql = "select * from user WHERE userInfo=? order by remarksInfo asc limit ?,?";
			}
			break;
		default:
			break;
		}
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class), managerType, firstPage * pageSize,
					pageSize);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addUserUsingTime(String userid, int day, Date expirationTime) {
		String sql = "UPDATE user SET `renewNumber`=renewNumber+?,`expirationTime`=? WHERE `userid`=?";
		try {
			return runner.update(sql, day, expirationTime, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean userLogin(String userid, Date loginTime) {
		String sql = "UPDATE user SET `loginNumber`=loginNumber+1,`loginTime`=? WHERE `userid`=?";
		try {
			return runner.update(sql, loginTime, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUserPhoneId(String userid, String phoneId) {
		String sql = "UPDATE user SET `phoneId`=? WHERE `userid`=?";
		try {
			return runner.update(sql, phoneId, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String userid) {
		String sql = "DELETE FROM `user` WHERE `userid`=?";
		try {
			return runner.update(sql, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserInfo(String userid, String userInfo) {
		String sql = "UPDATE user SET `userInfo`=? WHERE `userid`=?";
		try {
			return runner.update(sql, userInfo, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateRemarksInfo(String userid, String remarksInfo) {
		String sql = "UPDATE user SET `remarksInfo`=? WHERE `userid`=?";
		try {
			return runner.update(sql, remarksInfo, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserid(String userid, String newUserid) {
		String sql = "UPDATE user SET `userid`=? WHERE `userid`=?";
		try {
			return runner.update(sql, newUserid, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePassword(String userid, String password) {
		String sql = "UPDATE user SET `password`=? WHERE `userid`=?";
		try {
			return runner.update(sql, password, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getRenewExpirationUsersListNum() {
		String sql = "SELECT count(id) as id FROM yungou_assist.user where user.userid in (select userid from yungou_assist.renew group by userid) and expirationTime < now()";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> getRenewExpirationUsers() {
		String sql = "SELECT * FROM yungou_assist.user where user.userid in (select userid from yungou_assist.renew group by userid) and expirationTime < now()";
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public int getRenewFutureExpirationUsersListNum() {
		Date time = new Date();
		long time2 = time.getTime() + 3 * 24 * 60 * 60 * 1000;
		time = new Date(time2);
		String sql = "SELECT count(id) as id FROM yungou_assist.user where user.userid in (select userid from yungou_assist.renew group by userid) and expirationTime < ? and expirationTime > now()";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class), time).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int getRenewNotExpirationUsersListNum() {
		String sql = "SELECT count(id) as id FROM yungou_assist.user where user.userid in (select userid from yungou_assist.renew group by userid) and expirationTime > now()";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> getRenewFutureExpirationUsers() {
		Date time = new Date();
		long time2 = time.getTime() + 3 * 24 * 60 * 60 * 1000;
		time = new Date(time2);
		String sql = "SELECT * FROM yungou_assist.user where user.userid in (select userid from yungou_assist.renew group by userid) and expirationTime < ? and expirationTime > now()";
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class), time);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<User> getRenewByDayList() {
		String sql = "select DATE_FORMAT(registerTime,'%Y-%m-%d') as days,count(id) as id,DATE_FORMAT(registerTime,'%Y-%m-%d') as userid from user group by days desc";
		try {
			return runner.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}

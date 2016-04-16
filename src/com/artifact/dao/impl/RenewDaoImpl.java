package com.artifact.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.artifact.dao.RenewDao;
import com.artifact.model.Renew;
import com.artifact.util.JdbcUtil;

public class RenewDaoImpl implements RenewDao {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public Renew addRenew(String userid, float renewMoney, String userInfo) {
		String sql = "INSERT INTO `yungou_assist`.`renew` (`userid`, `renewMoney`,`userInfo`) VALUES (?, ?, ?)";
		try {
			return runner.insert(sql, new BeanHandler<Renew>(Renew.class),
					new Object[] { userid, renewMoney, userInfo });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Renew getRenew(String userid) {
		String sql = "select * from renew where userid = ?";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class), userid);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean setRenewIsDeal(String userInfo) {
		String sql = "UPDATE `yungou_assist`.`renew` SET `isDeal`=1, `dealTime`=now() WHERE `userInfo`=?";
		try {
			return runner.update(sql, userInfo) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Renew> getRenewList(int firstPage, int pageSize, int sortType, boolean rankType) {
		String sql = "select * from renew order by renewTime desc limit ?,?";
		try {
			return runner.query(sql, new BeanListHandler<Renew>(Renew.class), firstPage * pageSize, pageSize);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public int getRenewListNum() {
		String sql = "select count(id) as id from renew";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRenewListNum(String managerType) {
		String sql = "select count(id) as id from renew WHERE `userInfo`=?";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class), managerType).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTwoRenewListNum() {
		String sql = "select count(id) as id from (select * from renew group by userid having(count(id)>1)) a";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Renew> getTwoRenewList(int firstPage, int pageSize, int sortType, boolean rankType) {
		String sql = "SELECT count(id)as id,userid,sum(renewMoney) as renewMoney,userInfo FROM yungou_assist.renew  group by userid having(count(id)>1) limit ?,?";
		try {
			return runner.query(sql, new BeanListHandler<Renew>(Renew.class), firstPage * pageSize, pageSize);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public int getRenewMoney(String managerType) {
		String sql = "select sum(renewMoney) as id from renew WHERE  `userInfo`=?";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class), managerType).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRenewMoney() {
		String sql = "select sum(renewMoney) as id from renew";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRenewUseUserMoney() {
		String sql = "select sum(renewMoney) as id from renew where userid in (SELECT userid FROM yungou_assist.user where user.userid in (select userid from yungou_assist.renew group by userid) and expirationTime > now())";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRenewNotPayMoney() {
		String sql = "select sum(renewMoney) as id from renew WHERE isDeal=0";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRenewNotPayMoney(String managerType) {
		String sql = "select sum(renewMoney) as id from renew WHERE `userInfo`=? and isDeal=0";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class), managerType).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRenewPayMoney(String managerType) {
		String sql = "select sum(renewMoney) as id from renew WHERE `userInfo`=? and isDeal=1";
		try {
			return runner.query(sql, new BeanHandler<Renew>(Renew.class), managerType).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean deleteRenew(String id) {
		String sql = "DELETE FROM `yungou_assist`.`renew` WHERE `id`=?";
		try {
			return runner.update(sql, id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Renew> getRenewByDayList() {
		String sql = "select DATE_FORMAT(renewTime,'%Y-%m-%d') as days,count(id) as id,DATE_FORMAT(renewTime,'%Y-%m-%d') as userid from renew group by days";
		try {
			return runner.query(sql, new BeanListHandler<Renew>(Renew.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Renew> getRenewByUser() {
		String sql = "select * from renew GROUP BY userid";
		try {
			return runner.query(sql, new BeanListHandler<Renew>(Renew.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}

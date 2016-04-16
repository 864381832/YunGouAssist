package com.artifact.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.artifact.dao.UserGeneralizeDao;
import com.artifact.model.UserGeneralize;
import com.artifact.util.JdbcUtil;

public class UserGeneralizeDaoImpl implements UserGeneralizeDao {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public UserGeneralize addUserGeneralize(String userid, String generalizeId) {
		String sql = "INSERT INTO `yungou_assist`.`usergeneralize` (`userid`, `generalizeId`) VALUES (?, ?)";
		try {
			return runner.insert(sql, new BeanHandler<UserGeneralize>(UserGeneralize.class),
					new Object[] { userid, generalizeId });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateIsGetRewards(String userid) {
		String sql = "UPDATE `yungou_assist`.`usergeneralize` SET `isGetRewards`=1, `getRewardsTime`=now() WHERE `userid`=?";
		try {
			return runner.update(sql, userid) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserGeneralize getUserGeneralize(String userid) {
		String sql = "select * from usergeneralize where `userid` = ?";
		try {
			return runner.query(sql, new BeanHandler<UserGeneralize>(UserGeneralize.class), userid);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserGeneralize> getUserGeneralizes(String generalizeId) {
		String sql = "select * from usergeneralize where `generalizeId` = ?";
		try {
			return runner.query(sql, new BeanListHandler<UserGeneralize>(UserGeneralize.class), generalizeId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public int getUserGeneralizeListNum() {
		String sql = "select count(id) as id from usergeneralize";
		try {
			return runner.query(sql, new BeanHandler<UserGeneralize>(UserGeneralize.class)).getId();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<UserGeneralize> getUserGeneralizeList(int firstPage, int pageSize, int sortType, boolean rankType) {
		String sql = "select * from usergeneralize limit ?,?";
		try {
			return runner.query(sql, new BeanListHandler<UserGeneralize>(UserGeneralize.class), firstPage * pageSize,
					pageSize);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}

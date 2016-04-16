package com.artifact.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.artifact.dao.YungouPublishDao;
import com.artifact.model.YungouPublish;
import com.artifact.util.JdbcUtil;

/**
 * tableName 数据表格 yungou_publish一元云购 yigou_publish一元益购
 *
 */
public class YungouPublishDaoImpl implements YungouPublishDao {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public YungouPublish addYungouPublish(int goodsID, int codePeriod, int codeID, int codeRNO, int awardPosition,
			String userName, int buyCount, String buyStartPosition) {
		String sql = "INSERT INTO yungou_publish (`goodsID`, `codePeriod`, `codeID`, `codeRNO`, `awardPosition`, `userName`, `buyCount`, `buyStartPosition`) VALUES(?,?,?,?,?,?,?,?)";
		try {
			return runner.insert(sql, new BeanHandler<YungouPublish>(YungouPublish.class), new Object[] { goodsID,
					codePeriod, codeID, codeRNO, awardPosition, userName, buyCount, buyStartPosition });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public YungouPublish getYungouPublish(int goodsID, int codePeriod) {
		String sql = "SELECT * FROM yungou_publish where goodsId=? and codePeriod = ?";
		try {
			return runner.query(sql, new BeanHandler<YungouPublish>(YungouPublish.class),
					new Object[] { goodsID, codePeriod });
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<YungouPublish> getYungouPublishs(int goodsID, int selectNum) {
		String sql = "SELECT * FROM yungou_publish where goodsId=? order by codePeriod desc limit 0,?";
		try {
			return runner.query(sql, new BeanListHandler<YungouPublish>(YungouPublish.class),
					new Object[] { goodsID, selectNum });
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteYungouPublish(int goodsID, int codePeriod) {
		String sql = "DELETE FROM yungou_publish where goodsId=? and codePeriod = ?";
		try {
			runner.update(sql, goodsID, codePeriod);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}

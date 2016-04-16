package com.artifact.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.artifact.dao.GoodsInfoDao;
import com.artifact.model.GoodsInfo;
import com.artifact.util.JdbcUtil;

public class GoodsInfoDaoImpl implements GoodsInfoDao {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public GoodsInfo addGoodsInfo(int goodsID, String goodsSName, int codeID, int codeLimitBuy, String goodsPic,
			int isSale) {
		String sql = "INSERT INTO `goods_info` (`goodsID`, `goodsSName`, `codeID`, `codeLimitBuy`, `goodsPic`, `isSale`) VALUES (?,?,?,?,?,?)";
		try {
			return runner.insert(sql, new BeanHandler<GoodsInfo>(GoodsInfo.class),
					new Object[] { goodsID, goodsSName, codeID, codeLimitBuy, goodsPic, isSale });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GoodsInfo getGoodsInfo(int goodsID) {
		String sql = "SELECT * FROM goods_info where goodsID=?";
		try {
			return runner.query(sql, new BeanHandler<GoodsInfo>(GoodsInfo.class), goodsID);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public GoodsInfo getGoodsInfo(String goodsSName, String goodsPic) {
		String sql = "SELECT * FROM goods_info where goodsSName=? and goodsPic = ?";
		try {
			return runner.query(sql, new BeanHandler<GoodsInfo>(GoodsInfo.class), goodsSName, goodsPic);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public GoodsInfo getGoodsInfo(String goodsPic) {
		String sql = "SELECT * FROM goods_info where goodsPic = ?";
		try {
			return runner.query(sql, new BeanHandler<GoodsInfo>(GoodsInfo.class), goodsPic);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GoodsInfo> getGoodsInfos() {
		String sql = "SELECT * FROM goods_info";
		try {
			return runner.query(sql, new BeanListHandler<GoodsInfo>(GoodsInfo.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GoodsInfo> getGoodsInfosBySale() {
		String sql = "SELECT * FROM goods_info where isSale=1";
		try {
			return runner.query(sql, new BeanListHandler<GoodsInfo>(GoodsInfo.class));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteGoodsInfo(int goodsID) {
		String sql = "DELETE FROM goods_info where goodsId=?";
		try {
			runner.update(sql, goodsID);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void setGoodsInfoIsSale(int goodsID, int isSale) {
		String sql = "UPDATE goods_info SET `isSale`=? WHERE `goodsID`=?";
		try {
			runner.update(sql, isSale, goodsID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

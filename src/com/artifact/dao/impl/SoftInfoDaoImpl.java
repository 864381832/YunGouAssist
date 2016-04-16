package com.artifact.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.artifact.dao.SoftInfoDao;
import com.artifact.model.SoftInfo;
import com.artifact.util.JdbcUtil;

public class SoftInfoDaoImpl implements SoftInfoDao {
	private static QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

	@Override
	public SoftInfo getSoftInfo(String name) {
		String sql = "select * from soft_info where name = ?";
		try {
			return runner.query(sql, new BeanHandler<SoftInfo>(SoftInfo.class), name);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}

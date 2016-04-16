package com.artifact.util;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	private static DataSource dataSource = null;
	/*
	 * static { dataSource = new ComboPooledDataSource(); }
	 */

	public static void initDataSource() {
		dataSource = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return dataSource;
	}
}

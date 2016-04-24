package com.artifact.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.artifact.services.ManagerAjaxServices;
import com.artifact.services.impl.ManagerAjaxServicesImpl;
import com.artifact.yungou.YungouMonitoringServer;

@WebListener
public class InitDataSourceListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("应用关闭，销毁数据");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("应用启动，初始化数据");

		// ReadXml.initPhoneInfoXML(arg0.getServletContext());

		JdbcUtil.initDataSource();

		// YungouMonitoringServer.selectGoodsInfo();
		// YungouMonitoringServer.selectYungouRecord();

		// ManagerAjaxServicesImpl.getManagerAjaxServices().addUserTime();
	}

}

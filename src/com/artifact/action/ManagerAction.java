package com.artifact.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.artifact.global.config;
import com.artifact.model.Admin;
import com.artifact.services.impl.ManagerActionServicesImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport
		implements RequestAware, SessionAware, ApplicationAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response;

	private List<Map<String, Object>> payRecordList = new ArrayList<Map<String, Object>>();
	private HashMap<String, Object> otherData = new HashMap<String, Object>();

	/*
	 * 进入管理首页
	 */
	public String rootManager() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		setPageOtherData();
		if (otherData.get("selectText").equals("")) {
			int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices().getUserListNum(null);
			otherData.put("listSize", DrawingsRecordListSize);
			otherData.put("pages", (int) Math.ceil((float) DrawingsRecordListSize / config.userPageSize));
			payRecordList = ManagerActionServicesImpl.getManagerActionServices().getUserList(
					(int) otherData.get("pageNum") - 1, (int) otherData.get("sortType"),
					(int) otherData.get("rankType"), null);
			int renewNotExpirationUsersListNum = ManagerActionServicesImpl.getManagerActionServices().getRenewNotExpirationUsersListNum();
			otherData.put("renewNotExpirationUsersListNum", renewNotExpirationUsersListNum);
			int RenewListNum = ManagerActionServicesImpl.getManagerActionServices().getRenewListNum(null);
			otherData.put("RenewListNum", RenewListNum);
			otherData.put("RenewScale", RenewListNum * 100 / DrawingsRecordListSize);
			otherData.put("RenewMoney", ManagerActionServicesImpl.getManagerActionServices().getRenewMoney(null));
			otherData.put("RenewUseUserMoney", ManagerActionServicesImpl.getManagerActionServices().getRenewUseUserMoney());
		} else {
			payRecordList = ManagerActionServicesImpl.getManagerActionServices()
					.getUserInfo((String) otherData.get("selectText"), null);
		}
		return SUCCESS;
	}

	/*
	 * 进入代理商管理首页
	 */
	public String agentManager() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		String userInfo = ((Admin) session.get("userInfo")).getUserid();
		setPageOtherData();
		if (otherData.get("selectText").equals("")) {
			int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices().getUserListNum(userInfo);
			otherData.put("listSize", DrawingsRecordListSize);
			otherData.put("pages", (int) Math.ceil((float) DrawingsRecordListSize / config.userPageSize));
			payRecordList = ManagerActionServicesImpl.getManagerActionServices().getUserList(
					(int) otherData.get("pageNum") - 1, (int) otherData.get("sortType"),
					(int) otherData.get("rankType"), userInfo);
			int RenewListNum = ManagerActionServicesImpl.getManagerActionServices().getRenewListNum(userInfo);
			otherData.put("RenewListNum", RenewListNum);
			otherData.put("RenewMoney", ManagerActionServicesImpl.getManagerActionServices().getRenewMoney(userInfo));
			otherData.put("RenewPayMoney",
					ManagerActionServicesImpl.getManagerActionServices().getRenewPayMoney(userInfo));
		} else {
			payRecordList = ManagerActionServicesImpl.getManagerActionServices()
					.getUserInfo((String) otherData.get("selectText"), userInfo);
		}
		return "agentManagerIndex";
	}

	/*
	 * 进入付费用户页面
	 */
	public String renewPage() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		setPageOtherData();
		if (otherData.get("selectText").equals("")) {
			int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices().getRenewListNum(null);
			otherData.put("RenewListNum", DrawingsRecordListSize);
			otherData.put("pages", (int) Math.ceil((float) DrawingsRecordListSize / config.userPageSize));
			payRecordList = ManagerActionServicesImpl.getManagerActionServices().getRenewList(
					(int) otherData.get("pageNum") - 1, (int) otherData.get("sortType"),
					(int) otherData.get("rankType"), null);
			int userListSize = ManagerActionServicesImpl.getManagerActionServices().getUserListNum(null);
			int TwoRenewListNum = ManagerActionServicesImpl.getManagerActionServices().getTwoRenewListNum(null);
			otherData.put("TwoRenewListNum", TwoRenewListNum);
			otherData.put("RenewScale", DrawingsRecordListSize * 100 / userListSize);
			otherData.put("TwoRenewScale", TwoRenewListNum * 100 / DrawingsRecordListSize);
			otherData.put("RenewMoney", ManagerActionServicesImpl.getManagerActionServices().getRenewMoney(null));
		} else {
			payRecordList = ManagerActionServicesImpl.getManagerActionServices()
					.getUserInfo((String) otherData.get("selectText"), null);
		}
		return "renewPage";
	}

	/*
	 * 进入续费用户页面
	 */
	public String renewTwoPage() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		setPageOtherData();
		if (otherData.get("selectText").equals("")) {
			int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices().getTwoRenewListNum(null);
			otherData.put("pages", (int) Math.ceil((float) DrawingsRecordListSize / config.userPageSize));
			payRecordList = ManagerActionServicesImpl.getManagerActionServices().getTwoRenewList(
					(int) otherData.get("pageNum") - 1, (int) otherData.get("sortType"),
					(int) otherData.get("rankType"), null);
			int userListSize = ManagerActionServicesImpl.getManagerActionServices().getUserListNum(null);
			int RenewListNum = ManagerActionServicesImpl.getManagerActionServices().getRenewListNum(null);
			otherData.put("RenewListNum", RenewListNum);
			otherData.put("TwoRenewListNum", DrawingsRecordListSize);
			otherData.put("RenewScale", RenewListNum * 100 / userListSize);
			otherData.put("TwoRenewScale", DrawingsRecordListSize * 100 / RenewListNum);
			otherData.put("RenewMoney", ManagerActionServicesImpl.getManagerActionServices().getRenewMoney(null));
		} else {
			payRecordList = ManagerActionServicesImpl.getManagerActionServices()
					.getUserInfo((String) otherData.get("selectText"), null);
		}
		return "renewTwoPage";
	}

	/*
	 * 进入用户推广列表页面
	 */
	public String userGeneralizePage() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		setPageOtherData();
		if (otherData.get("selectText").equals("")) {
			int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices()
					.getUserGeneralizeListNum();
			otherData.put("listSize", DrawingsRecordListSize);
			otherData.put("pages", (int) Math.ceil((float) DrawingsRecordListSize / config.userPageSize));
			payRecordList = ManagerActionServicesImpl.getManagerActionServices().getUserGeneralizeList(
					(int) otherData.get("pageNum") - 1, (int) otherData.get("sortType"),
					(int) otherData.get("rankType"));
		} else {
			payRecordList = ManagerActionServicesImpl.getManagerActionServices()
					.getUserInfo((String) otherData.get("selectText"), null);
		}
		return "userGeneralizePage";
	}

	/*
	 * 进入续费到期用户表
	 */
	public String renewExpirationUserPage() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices()
				.getRenewExpirationUsersListNum();
		otherData.put("ListNum", DrawingsRecordListSize);
		payRecordList = ManagerActionServicesImpl.getManagerActionServices().getRenewExpirationUsers();
		return "renewExpirationUserPage";
	}

	/*
	 * 进入获取续费即将到期用户表
	 */
	public String renewFutureExpirationUserPage() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices()
				.getRenewFutureExpirationUsersListNum();
		otherData.put("ListNum", DrawingsRecordListSize);
		payRecordList = ManagerActionServicesImpl.getManagerActionServices().getRenewFutureExpirationUsers();
		return "renewFutureExpirationUserPage";
	}

	/*
	 * 进入按天趋势表
	 */
	public String renewByDayPage() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
		payRecordList = ManagerActionServicesImpl.getManagerActionServices().getRenewByDayList();
		int DrawingsRecordListSize = ManagerActionServicesImpl.getManagerActionServices().getUserListNum(null);
		otherData.put("listSize", DrawingsRecordListSize);
		int RenewListNum = ManagerActionServicesImpl.getManagerActionServices().getRenewListNum(null);
		int TwoRenewListNum = ManagerActionServicesImpl.getManagerActionServices().getTwoRenewListNum(null);
		otherData.put("RenewListNum", RenewListNum);
		otherData.put("TwoRenewListNum", TwoRenewListNum);
		otherData.put("RenewScale", RenewListNum * 100 / DrawingsRecordListSize);
		otherData.put("TwoRenewScale", TwoRenewListNum * 100 / RenewListNum);
		otherData.put("RenewMoney", ManagerActionServicesImpl.getManagerActionServices().getRenewMoney(null));
		return "renewByDayPage";
	}

	private void setPageOtherData() {
		if (otherData.get("selectText") == null) {
			otherData.put("selectText", "");
		}
		if (otherData.get("sortType") == null) {
			otherData.put("sortType", 0);
		} else {
			otherData.put("sortType", Integer.parseInt((String) otherData.get("sortType")));
		}
		if (otherData.get("rankType") == null) {
			otherData.put("rankType", 0);
		} else {
			otherData.put("rankType", Integer.parseInt((String) otherData.get("rankType")));
		}
		if (otherData.get("pageNum") == null) {
			otherData.put("pageNum", 1);
		} else {
			otherData.put("pageNum", Integer.parseInt((String) otherData.get("pageNum")));
		}
	}

	public List<Map<String, Object>> getPayRecordList() {
		return payRecordList;
	}

	public void setPayRecordList(List<Map<String, Object>> payRecordList) {
		this.payRecordList = payRecordList;
	}

	public HashMap<String, Object> getOtherData() {
		return otherData;
	}

	public void setOtherData(HashMap<String, Object> otherData) {
		this.otherData = otherData;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}

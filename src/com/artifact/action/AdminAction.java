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

import com.artifact.services.impl.AdminActionServicesImpl;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport
		implements RequestAware, SessionAware, ApplicationAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response;

	private List<Map<String, Object>> payRecordList = new ArrayList<Map<String, Object>>();
	private HashMap<String, Object> otherData = new HashMap<String, Object>();

	/*
	 * 进入代理管理首页
	 */
	public String managerAgent() throws Exception {
		if (session.get("userInfo") == null) {
			return "error";
		}
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
		if (otherData.get("selectText").equals("")) {
			if (otherData.get("pageNum") == null) {
				otherData.put("pageNum", 1);
			} else {
				otherData.put("pageNum", Integer.parseInt((String) otherData.get("pageNum")));
			}
			int DrawingsRecordListSize = AdminActionServicesImpl.getAdminActionServices().getAdminListNum();
			otherData.put("listSize", DrawingsRecordListSize);
			payRecordList = AdminActionServicesImpl.getAdminActionServices().getAdminList();
		} else {
			payRecordList = AdminActionServicesImpl.getAdminActionServices()
					.getAdminList((String) otherData.get("selectText"));
		}
		return SUCCESS;
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

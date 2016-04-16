package com.artifact.services;

import java.util.List;
import java.util.Map;

public interface AdminActionServices {
	public int getAdminListNum();

	public List<Map<String, Object>> getAdminList();

	public List<Map<String, Object>> getAdminList(String adminid);// 查询管理员信息
}

package com.artifact.services;

import java.util.List;
import java.util.Map;

import com.artifact.model.User;

public interface ManagerActionServices {
	public int getUserListNum(String managerType);// 获取所有用户提款申请数

	public List<Map<String, Object>> getUserList(int firstPage, int sortType, int rankType, String managerType);// 分页获取时间段内待打款记录

	public List<Map<String, Object>> getUserInfo(String userid, String managerType);// 查询用户信息

	public int getRenewListNum(String managerType);// 获取所有用户付费数

	public int getRenewMoney(String managerType);// 获取总收入

	public int getRenewPayMoney(String managerType);// 获取总收入

	public List<Map<String, Object>> getRenewList(int firstPage, int sortType, int rankType, String managerType);// 分页获取付费记录

	public int getTwoRenewListNum(String managerType);// 获取所有用户二次以上付费数

	public List<Map<String, Object>> getTwoRenewList(int firstPage, int sortType, int rankType, String managerType);// 分页获取二次付费记录

	public int getUserGeneralizeListNum();// 获取用户推广数

	public List<Map<String, Object>> getUserGeneralizeList(int firstPage, int sortType, int rankType);// 分页用户推广记录

	public int getRenewExpirationUsersListNum();// 获取续费到期用户个数

	public List<Map<String, Object>> getRenewExpirationUsers();// 获取续费到期用户表

	public int getRenewFutureExpirationUsersListNum();// 获取续费即将到期用户个数

	public int getRenewNotExpirationUsersListNum();// 获取续费未到期用户个数
	
	public int getRenewUseUserMoney();// 获取使用用户总收入
	
	public List<Map<String, Object>> getRenewFutureExpirationUsers();// 获取续费即将到期用户

	public List<Map<String, Object>> getRenewByDayList();// 分天获取用户列表
}

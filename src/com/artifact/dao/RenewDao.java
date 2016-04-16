package com.artifact.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.artifact.model.Renew;

/*
 * 续费表Dao
 */
public interface RenewDao {
	public Renew addRenew(String userid, float renewMoney, String userInfo);// 添加续费信息

	public Renew getRenew(String userid);// 获取续费用户

	public boolean setRenewIsDeal(String userInfo);// 设置处理代理回扣

	public int getRenewListNum();// 获取所有用户数

	public int getRenewListNum(String managerType);// 获取所有用户付费数

	public List<Renew> getRenewList(int firstPage, int pageSize, int sortType, boolean rankType);// 分页获取用户列表

	public int getTwoRenewListNum();// 获取所有用户二次以上付费数

	public List<Renew> getTwoRenewList(int firstPage, int pageSize, int sortType, boolean rankType);// 分页获取二次付费记录

	public int getRenewMoney();// 获取总收入
	
	public int getRenewUseUserMoney();// 获取使用用户总收入

	public int getRenewMoney(String managerType);// 获取总收入

	public int getRenewNotPayMoney();// 获取未打款收入

	public int getRenewNotPayMoney(String managerType);// 获取未打款收入

	public int getRenewPayMoney(String managerType);// 获取已打款收入

	public boolean deleteRenew(String id);// 删除记录

	public List<Renew> getRenewByDayList();// 分天获取用户列表
	
	public List<Renew> getRenewByUser();// 获取所有续费用户列表
}

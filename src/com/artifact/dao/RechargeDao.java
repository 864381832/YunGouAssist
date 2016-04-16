package com.artifact.dao;

import java.util.List;

import com.artifact.model.Recharge;

/*
 * 充值卡Dao接口
 */
public interface RechargeDao {
	public Recharge addRecharge(String account, String password, int moneyValue);// 添加充值卡

	public Recharge getRecharge(String account, String password);// 获取充值卡信息

	public void deleteRecharge(String goodsID, int codePeriod);// 删除云购记录

	public int getRechargeListNum();// 获取所有用户数

	public int getRechargeListNum(String managerType);// 获取充值卡数量

	public List<Recharge> getRechargeList(int firstPage, int pageSize, int sortType, boolean rankType);// 分页获取用户列表
}

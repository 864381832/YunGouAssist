package com.artifact.dao;

import java.util.List;

import com.artifact.model.UserGeneralize;

/*
 * 用户推广记录Dao
 */
public interface UserGeneralizeDao {
	public UserGeneralize addUserGeneralize(String userid, String generalizeId);// 添加推广记录

	public boolean updateIsGetRewards(String userid);// 更新领取奖励

	public UserGeneralize getUserGeneralize(String userid);

	public List<UserGeneralize> getUserGeneralizes(String generalizeId);// 根据用户信息获取所以推广码

	public int getUserGeneralizeListNum();// 获取所有用户数

	public List<UserGeneralize> getUserGeneralizeList(int firstPage, int pageSize, int sortType, boolean rankType);// 分页获取用户列表
}

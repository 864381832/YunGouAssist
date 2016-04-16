package com.artifact.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.artifact.dao.RenewDao;
import com.artifact.dao.UserDao;
import com.artifact.dao.UserGeneralizeDao;
import com.artifact.dao.impl.RenewDaoImpl;
import com.artifact.dao.impl.UserDaoImpl;
import com.artifact.dao.impl.UserGeneralizeDaoImpl;
import com.artifact.global.config;
import com.artifact.model.Renew;
import com.artifact.model.User;
import com.artifact.model.UserGeneralize;
import com.artifact.services.ManagerActionServices;

public class ManagerActionServicesImpl implements ManagerActionServices {
	static private ManagerActionServices managerActionServices;

	private UserDao userDao = null;
	private RenewDao renewDao = null;
	private UserGeneralizeDao userGeneralizeDao = null;

	public ManagerActionServicesImpl() {
		super();
		this.userDao = new UserDaoImpl();
		this.renewDao = new RenewDaoImpl();
		this.userGeneralizeDao = new UserGeneralizeDaoImpl();
	}

	public static ManagerActionServices getManagerActionServices() {
		if (managerActionServices == null) {
			managerActionServices = new ManagerActionServicesImpl();
		}
		return managerActionServices;
	}

	@Override
	public int getUserListNum(String managerType) {
		if (managerType == null) {
			return userDao.getUserListNum();
		} else {
			return userDao.getUserListNum(managerType);
		}
	}

	@Override
	public List<Map<String, Object>> getUserList(int firstPage, int sortType, int rankType, String managerType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<User> userList = null;
		if (managerType == null) {
			userList = userDao.getUserList(firstPage, config.userPageSize, sortType, rankType == 0);
		} else {
			userList = userDao.getUserList(firstPage, config.userPageSize, sortType, rankType == 0, managerType);
		}
		Date nowTime = new Date();
		for (User user : userList) {
			// if (user.getUserType() == 1) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Userid", user.getUserid());
			map.put("password", user.getPassword());
			map.put("phoneId", user.getPhoneId());
			map.put("registerTime", user.getRegisterTime());
			map.put("expirationTime", user.getExpirationTime());
			map.put("loginNumber", user.getLoginNumber());
			map.put("loginTime", user.getLoginTime());
			long time = user.getExpirationTime().getTime() - nowTime.getTime();
			if (time > 0) {
				map.put("surplusDay", (int) Math.ceil(time / (1000 * 60 * 60 * 24)) + 1);
			} else {
				map.put("surplusDay", "已到期");
			}
			map.put("renewNumber", user.getRenewNumber());
			map.put("userInfo", user.getUserInfo());
			map.put("remarksInfo", user.getRemarksInfo());
			dataList.add(map);
			// }
		}
		return dataList;
	}

	@Override
	public List<Map<String, Object>> getUserInfo(String userid, String managerType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<User> userList = null;
		if (managerType == null) {
			userList = userDao.fuzzyQueryUsers(userid);
		} else {
			userList = userDao.fuzzyQueryUsers(userid, managerType);
		}
		Date nowTime = new Date();
		for (User users : userList) {
			// if (users.getUserType() == 1) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Userid", users.getUserid());
			map.put("password", users.getPassword());
			map.put("phoneId", users.getPhoneId());
			map.put("registerTime", users.getRegisterTime());
			map.put("expirationTime", users.getExpirationTime());
			map.put("loginNumber", users.getLoginNumber());
			map.put("loginTime", users.getLoginTime());
			long time = users.getExpirationTime().getTime() - nowTime.getTime();
			if (time > 0) {
				map.put("surplusDay", (int) Math.ceil(time / (1000 * 60 * 60 * 24)) + 1);
			} else {
				map.put("surplusDay", "已到期");
			}
			map.put("renewNumber", users.getRenewNumber());
			map.put("userInfo", users.getUserInfo());
			map.put("remarksInfo", users.getRemarksInfo());
			dataList.add(map);
			// }
		}

		return dataList;
	}

	@Override
	public int getRenewListNum(String managerType) {
		if (managerType == null) {
			return renewDao.getRenewListNum();
		} else {
			return renewDao.getRenewListNum(managerType);
		}
	}

	@Override
	public int getTwoRenewListNum(String managerType) {
		return renewDao.getTwoRenewListNum();
	}

	@Override
	public int getRenewMoney(String managerType) {
		if (managerType == null) {
			return renewDao.getRenewMoney();
		} else {
			return renewDao.getRenewMoney(managerType);
		}
	}

	@Override
	public int getRenewPayMoney(String managerType) {
		return renewDao.getRenewPayMoney(managerType);
	}

	@Override
	public List<Map<String, Object>> getRenewList(int firstPage, int sortType, int rankType, String managerType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Renew> userList = null;
		if (managerType == null) {
			userList = renewDao.getRenewList(firstPage, config.userPageSize, sortType, rankType == 0);
		} else {
			// userList = renewDao.getRenewList(firstPage, config.userPageSize,
			// sortType, rankType == 0, managerType);
		}
		for (Renew user : userList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("Userid", user.getUserid());
			map.put("RenewMoney", user.getRenewMoney());
			map.put("RenewTime", user.getRenewTime());
			map.put("UserInfo", user.getUserInfo());
			if (user.getIsDeal() == 0) {
				map.put("IsDeal", "未结算");
			} else {
				map.put("IsDeal", "已结算");
			}
			map.put("DealTime", user.getDealTime());
			dataList.add(map);
		}
		return dataList;
	}

	@Override
	public List<Map<String, Object>> getTwoRenewList(int firstPage, int sortType, int rankType, String managerType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Renew> userList = null;
		if (managerType == null) {
			userList = renewDao.getTwoRenewList(firstPage, config.userPageSize, sortType, rankType == 0);
		} else {
			// userList = renewDao.getRenewList(firstPage, config.userPageSize,
			// sortType, rankType == 0, managerType);
		}
		for (Renew user : userList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("Userid", user.getUserid());
			map.put("RenewMoney", user.getRenewMoney());
			map.put("UserInfo", user.getUserInfo());
			dataList.add(map);
		}
		return dataList;
	}

	@Override
	public int getUserGeneralizeListNum() {
		return userGeneralizeDao.getUserGeneralizeListNum();
	}

	@Override
	public List<Map<String, Object>> getUserGeneralizeList(int firstPage, int sortType, int rankType) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<UserGeneralize> userList = null;
		userList = userGeneralizeDao.getUserGeneralizeList(firstPage, config.userPageSize, sortType, rankType == 0);
		for (UserGeneralize user : userList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Userid", user.getUserid());
			map.put("RenewMoney", user.getGeneralizeId());
			map.put("RenewTime", user.getGeneralizeTime());
			if (user.getIsGetRewards() == 0) {
				map.put("IsDeal", "未领取");
			} else {
				map.put("IsDeal", "已领取");
			}
			map.put("DealTime", user.getGetRewardsTime());
			dataList.add(map);
		}
		return dataList;
	}

	@Override
	public int getRenewExpirationUsersListNum() {
		return userDao.getRenewExpirationUsersListNum();
	}

	@Override
	public List<Map<String, Object>> getRenewExpirationUsers() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<User> userList = userDao.getRenewExpirationUsers();
		for (User users : userList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Userid", users.getUserid());
			map.put("registerTime", users.getRegisterTime());
			map.put("expirationTime", users.getExpirationTime());
			map.put("loginNumber", users.getLoginNumber());
			map.put("loginTime", users.getLoginTime());
			map.put("surplusDay", "已到期");
			map.put("renewNumber", users.getRenewNumber());
			map.put("userInfo", users.getUserInfo());
			map.put("remarksInfo", users.getRemarksInfo());
			ManagerAjaxServicesImpl.getManagerAjaxServices().updateUsingTime(users.getUserid(),31,0);
			dataList.add(map);
		}
		return dataList;
	}

	@Override
	public int getRenewFutureExpirationUsersListNum() {
		return userDao.getRenewFutureExpirationUsersListNum();
	}

	public int getRenewNotExpirationUsersListNum() {
		return userDao.getRenewNotExpirationUsersListNum();
	}
	
	public int getRenewUseUserMoney(){
		return renewDao.getRenewUseUserMoney();
	}

	@Override
	public List<Map<String, Object>> getRenewFutureExpirationUsers() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<User> userList = userDao.getRenewFutureExpirationUsers();
		Date nowTime = new Date();
		for (User users : userList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Userid", users.getUserid());
			map.put("registerTime", users.getRegisterTime());
			map.put("expirationTime", users.getExpirationTime());
			map.put("loginNumber", users.getLoginNumber());
			map.put("loginTime", users.getLoginTime());
			long time = users.getExpirationTime().getTime() - nowTime.getTime();
			map.put("surplusDay", (int) Math.ceil(time / (1000 * 60 * 60 * 24)) + 1);
			map.put("renewNumber", users.getRenewNumber());
			map.put("userInfo", users.getUserInfo());
			map.put("remarksInfo", users.getRemarksInfo());
			dataList.add(map);
		}
		return dataList;
	}

	@Override
	public List<Map<String, Object>> getRenewByDayList() {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<User> userList = userDao.getRenewByDayList();
		List<Renew> renewList = renewDao.getRenewByDayList();
		for (User users : userList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Userid", users.getUserid());
			map.put("RenewMoney", users.getId());
			boolean istrue = true;
			for (Renew renew : renewList) {
				if (renew.getUserid().equals(users.getUserid())) {
					map.put("UserInfo", renew.getId());
					map.put("loginNumber", renew.getId() * 100 / users.getId());
					istrue = false;
				}
			}
			if (istrue) {
				map.put("UserInfo", "0");
				map.put("loginNumber", "0");
			}
			dataList.add(map);
		}
		return dataList;
	}

}

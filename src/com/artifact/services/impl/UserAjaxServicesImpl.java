package com.artifact.services.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.artifact.dao.RenewDao;
import com.artifact.dao.SoftInfoDao;
import com.artifact.dao.UserDao;
import com.artifact.dao.UserGeneralizeDao;
import com.artifact.dao.impl.RenewDaoImpl;
import com.artifact.dao.impl.SoftInfoDaoImpl;
import com.artifact.dao.impl.UserDaoImpl;
import com.artifact.dao.impl.UserGeneralizeDaoImpl;
import com.artifact.global.CacheSelectData;
import com.artifact.model.SoftInfo;
import com.artifact.model.User;
import com.artifact.model.UserGeneralize;
import com.artifact.services.UserAjaxServices;
import com.artifact.util.ActionUtil;
import com.artifact.util.Signature;
import com.google.gson.Gson;

public class UserAjaxServicesImpl implements UserAjaxServices {
	static private UserAjaxServices userAjaxServices;

	private UserDao userDao = null;
	private SoftInfoDao softInfoDao = null;
	private RenewDao renewDao = null;
	private UserGeneralizeDao userGeneralizeDao = null;

	public UserAjaxServicesImpl() {
		super();
		this.userDao = new UserDaoImpl();
		this.softInfoDao = new SoftInfoDaoImpl();
		this.renewDao = new RenewDaoImpl();
		this.userGeneralizeDao = new UserGeneralizeDaoImpl();
	}

	public static UserAjaxServices getUserAjaxServices() {
		if (userAjaxServices == null) {
			userAjaxServices = new UserAjaxServicesImpl();
		}
		return userAjaxServices;
	}

	@Override
	public String login(String userid, String password, String time, String isLogin, String sig) {
		if (Signature.checkUrlSignature(userid, password, time, isLogin, sig)) {
			User user = userDao.getUser(userid, password);
			if (user != null && user.getUserType() == 1) {
				long nowTime = System.currentTimeMillis();
				if (nowTime > user.getExpirationTime().getTime()) {
					CacheSelectData.removeUserCache(userid);
					return "802";// 账户已过期
				} else {
					userDao.addUserPhoneId(userid, isLogin);
					userDao.userLogin(userid, new Date(nowTime));

					user.setPhoneId(isLogin);
					CacheSelectData.addtUserCache(userid, user);
					return loginSuccess(userid, user.getExpirationTime().getTime());// 登录成功
				}
			} else {
				return "801";// 账户名或密码错误
			}
		} else {
			return "804";// 验证出现问题
		}
	}

	@Override
	public String getUserExpirationTime(String userid, String password, String time, String isLogin, String sig) {
		if (Signature.checkUrlSignature(userid, password, time, isLogin, sig)) {
			User user = CacheSelectData.getUserCache(userid);
			if (user == null) {
				user = userDao.getUser(userid, password);
			}
			if (user != null && user.getUserType() == 1) {
				long nowTime = System.currentTimeMillis();
				if (nowTime > user.getExpirationTime().getTime()) {
					CacheSelectData.removeUserCache(userid);
					return "802";// 账户已过期
				} else {
					if (isLogin.equals(user.getPhoneId())) {
						long expirationTime = user.getExpirationTime().getTime() - nowTime;
						return "" + expirationTime + "___" + Signature.getServerSignature(userid, expirationTime);
					} else {
						return "803";// 该账户已登录
					}
				}
			} else {
				return "801";// 账户名或密码错误
			}
		} else {
			return "804";// 验证出现问题
		}
	}

	private String loginSuccess(String userid, long nowTime) {
		String sig = "" + nowTime + "___" + Signature.getServerSignature(userid, nowTime);
		return sig;
	}

	@Override
	public User getUser(String userid) {
		return userDao.getUser(userid);
	}

	@Override
	public String getSoftVersions() {
		return softInfoDao.getSoftInfo("assist_versions").getValue();
	}

	@Override
	public String getSoftDownloadUrl() {
		SoftInfo softInfo = softInfoDao.getSoftInfo("assist_versions");
		return softInfo.getValue() + "___" + softInfo.getValue2();
	}

	@Override
	public String getSoftInfo(String userid) {
		return softInfoDao.getSoftInfo(userid).getValue();
	}

	@Override
	public String getSoftInfo2(String userid) {
		SoftInfo softInfo = softInfoDao.getSoftInfo(userid);
		return softInfo.getValue() + "___" + softInfo.getValue2();
	}

	@Override
	public String registerUser(String userid, String password, String time, String isLogin, String sig,
			String userInfo) {
		User user = userDao.getUser(userid);
		long nowTimeLong = System.currentTimeMillis();
		if (user == null) {
			BigInteger expirationTime = BigInteger.valueOf(7 * 24 * 60 * 60 * 1000);
			expirationTime = expirationTime.add(BigInteger.valueOf(nowTimeLong));
			Date nowTime = new Date(expirationTime.longValue());
			userDao.addUser(userid, password, nowTime, userInfo, "");
			userDao.addUserPhoneId(userid, isLogin);
			userDao.userLogin(userid, nowTime);

			CacheSelectData.addtUserCache(userid, new User(userid, password, nowTime, isLogin));
			return loginSuccess(userid, nowTime.getTime());// 登录成功
		} else {
			userDao.updatePassword(userid, password);
			if (nowTimeLong > user.getExpirationTime().getTime()) {
				return "802";// 账户已过期
			} else {
				userDao.addUserPhoneId(userid, isLogin);
				userDao.userLogin(userid, new Date(nowTimeLong));

				user.setPhoneId(isLogin);
				CacheSelectData.addtUserCache(userid, user);
				return loginSuccess(userid, user.getExpirationTime().getTime());// 登录成功
			}
		}
	}

	@Override
	public boolean checkUser(String userid, String password, String time, String isLogin, String sig) {
		if (Signature.checkUrlSignature(userid, password, time, isLogin, sig)) {
			User user = CacheSelectData.getUserCache(userid);
			if (user == null) {
				user = userDao.getUser(userid, password);
			}
			if (user != null && user.getUserType() == 1) {
				if (System.currentTimeMillis() < user.getExpirationTime().getTime()) {
					if (isLogin.equals(user.getPhoneId())) {
						return true;
					}
				}
			}
		}
		return false;// 账户已过期
	}

	@Override
	public boolean addUserGeneralize(String userid, String userInfo) {
		try {
			User user = userDao.getUser(Integer.parseInt(userInfo) - 100000);
			if (user != null && !userid.equals(user.getUserid())) {
				if (userGeneralizeDao.getUserGeneralize(userid) == null) {
					userGeneralizeDao.addUserGeneralize(userid, userInfo);
					User user2 = userDao.getUser(userid);
					long addTime = user2.getExpirationTime().getTime();
					return userDao.addUserUsingTime(userid, 2, ActionUtil.getExpirationDate(addTime, 2));
				}
			} else {
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public String getUserGeneralizes(String userid, String password, String time, String isLogin, String sig) {
		if (Signature.checkUrlSignature(userid, password, time, isLogin, sig)) {
			ArrayList<String[]> stringList = new ArrayList<String[]>();
			try {
				User user = userDao.getUser(userid);
				List<UserGeneralize> userGeneralizeList = userGeneralizeDao
						.getUserGeneralizes("" + (user.getId() + 100000));
				for (UserGeneralize userGeneralize : userGeneralizeList) {
					String[] strings = new String[] { userGeneralize.getUserid(),
							"" + userGeneralize.getGeneralizeTime().getTime(), "" + userGeneralize.getIsGetRewards(),
							"" + (userGeneralize.getGetRewardsTime() == null ? "null"
									: userGeneralize.getGetRewardsTime().getTime()) };
					stringList.add(strings);
				}
				String string = new Gson().toJson(stringList);
				return string;
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public boolean updateIsGetRewards(String userid, String password, String time, String isLogin, String sig,
			String userInfo) {
		if (Signature.checkUrlSignature(userid, password, time, isLogin, sig)) {
			UserGeneralize userGeneralize = userGeneralizeDao.getUserGeneralize(userInfo);
			if (userGeneralize != null && userGeneralize.getIsGetRewards() == 0) {
				User user = userDao.getUser(userid);
				if (userGeneralize.getGeneralizeId().equals("" + (user.getId() + 100000))) {
					long addTime = user.getExpirationTime().getTime();
					userDao.addUserUsingTime(userid, 3, ActionUtil.getExpirationDate(addTime, 3));
					userGeneralizeDao.updateIsGetRewards(userInfo);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getUserGeneralize(String userid) {
		User user = userDao.getUser(userid);
		if (user != null) {
			return "" + (user.getId() + 100000);
		}
		return null;
	}

	@Override
	public boolean getIsBuyAssist(String userid) {
		if (renewDao.getRenew(userid) != null) {
			return true;
		}
		return false;
	}
}

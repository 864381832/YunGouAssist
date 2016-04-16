package com.artifact.netty.server;

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
import com.artifact.model.SoftInfo;
import com.artifact.model.User;
import com.artifact.model.UserGeneralize;
import com.artifact.util.ActionUtil;
import com.artifact.util.Signature;
import com.google.gson.Gson;

public class UserServices {
	static private UserServices userServices;

	private UserDao userDao = null;
	private SoftInfoDao softInfoDao = null;
	private RenewDao renewDao = null;
	private UserGeneralizeDao userGeneralizeDao = null;

	public UserServices() {
		super();
		this.userDao = new UserDaoImpl();
		this.softInfoDao = new SoftInfoDaoImpl();
		this.renewDao = new RenewDaoImpl();
		this.userGeneralizeDao = new UserGeneralizeDaoImpl();
	}

	public static UserServices getUserServices() {
		if (userServices == null) {
			userServices = new UserServices();
		}
		return userServices;
	}

	/*
	 * 用户登录
	 */
	public boolean login(String userid, String password, String isLogin) {
		User user = userDao.getUser(userid, password);
		if (user != null && user.getUserType() == 1) {
			Date nowTime = new Date();
			userDao.addUserPhoneId(userid, isLogin);
			userDao.userLogin(userid, nowTime);
			return true;
		} else {
			return false;// 账户名或密码错误
		}
	}

	/*
	 * 获取用户剩余时间
	 */
	public String getUserExpirationTime(String userid, String password) {
		User user = userDao.getUser(userid, password);
		if (user != null && user.getUserType() == 1) {
			return "" + user.getExpirationTime().getTime();
		}
		return null;
	}

	private String loginSuccess(String userid, Date nowTime) {
		String sig = "" + nowTime.getTime() + "___" + Signature.getServerSignature(userid, nowTime.getTime());
		return sig;
	}

	public User getUser(String userid) {
		return userDao.getUser(userid);
	}

	public String getSoftVersions() {
		return softInfoDao.getSoftInfo("assist_versions").getValue();
	}

	public String getSoftDownloadUrl() {
		SoftInfo softInfo = softInfoDao.getSoftInfo("assist_versions");
		return softInfo.getValue() + "___" + softInfo.getValue2();
	}

	public String getSoftInfo(String userid) {
		return softInfoDao.getSoftInfo(userid).getValue();
	}

	public String getSoftInfo2(String userid) {
		SoftInfo softInfo = softInfoDao.getSoftInfo(userid);
		return softInfo.getValue() + "___" + softInfo.getValue2();
	}

	public String registerUser(String userid, String password, String time, String isLogin, String sig,
			String userInfo) {
		User user = userDao.getUser(userid);
		Date nowTime = new Date();
		if (user == null) {
			BigInteger expirationTime = new BigInteger("" + (24 * 60 * 60 * 1000));
			expirationTime = expirationTime.add(new BigInteger("" + nowTime.getTime()));
			nowTime = new Date(expirationTime.longValue());
			userDao.addUser(userid, password, nowTime, userInfo, "");
			userDao.addUserPhoneId(userid, isLogin);
			userDao.userLogin(userid, nowTime);
			return loginSuccess(userid, nowTime);// 登录成功
		} else {
			userDao.updatePassword(userid, password);
			if (nowTime.getTime() > user.getExpirationTime().getTime()) {
				return "802";// 账户已过期
			} else {
				userDao.addUserPhoneId(userid, isLogin);
				userDao.userLogin(userid, nowTime);
				return loginSuccess(userid, user.getExpirationTime());// 登录成功
			}
		}
	}

	public boolean checkUser(String userid, String password, String time, String isLogin, String sig) {
		if (Signature.checkUrlSignature(userid, password, time, isLogin, sig)) {
			User user = userDao.getUser(userid, password);
			if (user != null && user.getUserType() == 1) {
				if (new Date().getTime() < user.getExpirationTime().getTime()) {
					if (isLogin.equals(user.getPhoneId())) {
						return true;
					}
				}
			}
		}
		return false;// 账户已过期
	}

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

	public String getUserGeneralize(String userid) {
		User user = userDao.getUser(userid);
		if (user != null) {
			return "" + (user.getId() + 100000);
		}
		return null;
	}

	public boolean getIsBuyAssist(String userid) {
		if (renewDao.getRenew(userid) != null) {
			return true;
		}
		return false;
	}
}

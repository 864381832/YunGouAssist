package com.artifact.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.artifact.services.impl.UserAjaxServicesImpl;
import com.artifact.services.impl.YungouPublishAjaxServicesImpl;
import com.artifact.util.MyStringUtil;
import com.artifact.yungou.CacheData;
import com.opensymphony.xwork2.ActionSupport;

public class YungouPublishAjax extends ActionSupport
		implements ServletRequestAware, SessionAware, ApplicationAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response;
	private String userid;
	private String password;
	private String time;
	private String isLogin;
	private String sig;
	private String userInfo;
	private int goodsID;// 商品id
	private int codePeriod;// 期数
	private int codeID;// 云购id
	private int codeRNO;// 幸运云购码
	private int awardPosition;// 中奖位置
	private String userName;// 中奖人姓名
	private int buyCount;// 中奖人本次购买人次
	private String buyStartPosition;// 购买起始位置
	private int selectNum;// 查询数量
	private HashMap<String, Object> otherData;// 临时数据储存

	/*
	 * 添加云购揭晓记录
	 */
	public void addYungouPublishs() {
		String loginInfo = null;
		try {
			if (awardPosition > 0 && buyCount > 0) {
				if (CacheData.getYungouPublishCacheDate(goodsID, codePeriod) == null) {
					if (UserAjaxServicesImpl.getUserAjaxServices().checkUser(userid, password, time, isLogin, sig)) {
						loginInfo = YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices().addYungouPublishs(
								goodsID, codePeriod, codeID, codeRNO, awardPosition,
								MyStringUtil.changeCharset(userName, "ISO-8859-1", "UTF-8"), buyCount,
								buyStartPosition);
					}
				}
			}
		} catch (Exception e) {
		}
		// System.out.println("用户" + userid + ",s登录代码：" + loginInfo);
		printString(loginInfo);
	}

	/*
	 * 获取云购揭晓记录
	 */
	public void getYungouPublishs() {
		String loginInfo = null;
		try {
			if (UserAjaxServicesImpl.getUserAjaxServices().checkUser(userid, password, time, isLogin, sig)) {
				loginInfo = YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices().getYungouPublishs(goodsID,
						codePeriod, selectNum);
			}
		} catch (Exception e) {
		}
		printString(MyStringUtil.changeCharset(loginInfo, "UTF-8", "ISO-8859-1"));
	}

	/*
	 * 删除错误云购记录
	 */
	public void deleteYungouPublish() {
		try {
			if (UserAjaxServicesImpl.getUserAjaxServices().checkUser(userid, password, time, isLogin, sig)) {
				YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices().deleteYungouPublish(goodsID, codePeriod);
			}
		} catch (Exception e) {
		}
	}

	/*
	 * 获取未查询到的数据
	 */
	public void getYungouPublishsNotData() {
		String loginInfo = null;
		try {
//			if (UserAjaxServicesImpl.getUserAjaxServices().checkUser(userid, password, time, isLogin, sig)) {
				loginInfo = YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices()
						.getYungouPublishsNotData(goodsID, codePeriod);
//			}
		} catch (Exception e) {
		}
		printString(loginInfo);
	}

	/*
	 * 获取商品信息
	 */
	public void getGoodsInfos() {
		String loginInfo = null;
		try {
			if (UserAjaxServicesImpl.getUserAjaxServices().checkUser(userid, password, time, isLogin, sig)) {
				loginInfo = YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices().getGoodsInfos(goodsID);
			}
		} catch (Exception e) {
		}
		printString(MyStringUtil.changeCharset(loginInfo, "UTF-8", "ISO-8859-1"));
	}

	/*
	 * 添加商品信息
	 */
	public void addGoodsInfos() {
		try {
			if (UserAjaxServicesImpl.getUserAjaxServices().checkUser(userid, password, time, isLogin, sig)) {
				YungouPublishAjaxServicesImpl.getYungouPublishAjaxServices()
						.addGoodsInfo(MyStringUtil.changeCharset(userInfo, "ISO-8859-1", "UTF-8"));
			}
		} catch (Exception e) {
		}
	}

	/*
	 * 向页面输出
	 */
	private void printString(Object str) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(str);
		out.close();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public int getCodePeriod() {
		return codePeriod;
	}

	public void setCodePeriod(int codePeriod) {
		this.codePeriod = codePeriod;
	}

	public int getAwardPosition() {
		return awardPosition;
	}

	public void setAwardPosition(int awardPosition) {
		this.awardPosition = awardPosition;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}

	public int getCodeID() {
		return codeID;
	}

	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}

	public int getCodeRNO() {
		return codeRNO;
	}

	public void setCodeRNO(int codeRNO) {
		this.codeRNO = codeRNO;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public String getBuyStartPosition() {
		return buyStartPosition;
	}

	public void setBuyStartPosition(String buyStartPosition) {
		this.buyStartPosition = buyStartPosition;
	}

	public int getSelectNum() {
		return selectNum;
	}

	public void setSelectNum(int selectNum) {
		this.selectNum = selectNum;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
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

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HashMap<String, Object> getOtherData() {
		return otherData;
	}

	public void setOtherData(HashMap<String, Object> otherData) {
		this.otherData = otherData;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}

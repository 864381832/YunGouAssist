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

import com.artifact.model.User;
import com.artifact.services.impl.ManagerAjaxServicesImpl;
import com.artifact.services.impl.UserAjaxServicesImpl;
import com.artifact.util.MyStringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AlipayNotifyAjax extends ActionSupport
		implements ServletRequestAware, SessionAware, ApplicationAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response;
	private String tradeNo;
	private String Money;
	private String title;
	private String memo;
	private String alipay_account;
	private String tenpay_account;
	private String Gateway;
	private String Sign;
	private HashMap<String, Object> otherData;// 临时数据储存

	/*
	 * 支付成功回调
	 */
	public void paySuccess() {
		// System.out.println("用户" + userid + ",s登录代码：" + loginInfo);
		String s = "AlipayNotifyAjax [tradeNo=" + tradeNo + ", Money=" + Money + ", title=" + title + ", memo=" + memo
				+ ", alipay_account=" + alipay_account + ", tenpay_account=" + tenpay_account + ", Gateway=" + Gateway
				+ ", Sign=" + Sign + "]";
		System.out.println(s);
		String ss = MyStringUtil
				.getMd5Encode("10001" + "e10adc3949ba59abbe56e057f20f883e" + tradeNo + Money + title + memo);
		System.out.println(ss.toUpperCase());
		System.out.println(Sign);
		User user = UserAjaxServicesImpl.getUserAjaxServices().getUser(title);
		if (user == null) {
			printString("IncorrectOrder");
		} else {
			if (Sign.equals(ss.toUpperCase())) {
				if (Integer.parseInt(Money) == 59) {
					boolean isPay = ManagerAjaxServicesImpl.getManagerAjaxServices().updateUsingTime(title, 31,
							Integer.parseInt(Money));
					ManagerAjaxServicesImpl.getManagerAjaxServices().updateRemarksInfo(title, "支付宝:" + alipay_account);
					if (isPay) {
						printString("Success");
						return;
					}
				}
			}
			printString("Fail");
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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMoney() {
		return Money;
	}

	public void setMoney(String money) {
		Money = money;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAlipay_account() {
		return alipay_account;
	}

	public void setAlipay_account(String alipay_account) {
		this.alipay_account = alipay_account;
	}

	public String getTenpay_account() {
		return tenpay_account;
	}

	public void setTenpay_account(String tenpay_account) {
		this.tenpay_account = tenpay_account;
	}

	public String getGateway() {
		return Gateway;
	}

	public void setGateway(String gateway) {
		Gateway = gateway;
	}

	public String getSign() {
		return Sign;
	}

	public void setSign(String sign) {
		Sign = sign;
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

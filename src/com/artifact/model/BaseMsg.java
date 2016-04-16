package com.artifact.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by yaozb on 15-4-11. 必须实现序列,serialVersionUID 一定要有
 */

public class BaseMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum MsgType {// 消息类型
		LOGIN, PING, ASK, REPLY
	}

	private MsgType type;
	// 必须唯一，否者会出现channel调用混乱
	private String clientId;
	private HashMap<String, String> data = new HashMap<String, String>();

	// 初始化客户端id
	public BaseMsg() {
		// this.clientId = "";
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public String getData(String key) {
		return data.get(key);
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	public void setData(String key, String value) {
		this.data.put(key, value);
	}
}

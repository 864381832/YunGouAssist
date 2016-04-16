package com.artifact.services;

import com.artifact.model.Admin;

public interface IndexActionServices {
	public Admin login(String userAccount, String userPassword);// 用户登录
	public String getDownloadUrl(String name);// 获取下载地址
}

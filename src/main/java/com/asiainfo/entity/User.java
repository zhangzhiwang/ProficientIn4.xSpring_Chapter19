package com.asiainfo.entity;

import java.util.List;

public class User {
	private int userId;
	private String userName;
	private String password;
	private List<LoginLog> loginLogList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<LoginLog> getLoginLogList() {
		return loginLogList;
	}

	public void setLoginLogList(List<LoginLog> loginLogList) {
		this.loginLogList = loginLogList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", loginLogList=" + loginLogList + "]";
	}

}

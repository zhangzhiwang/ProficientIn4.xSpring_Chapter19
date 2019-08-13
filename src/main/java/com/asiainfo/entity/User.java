package com.asiainfo.entity;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

//@XStreamAlias("user")
public class User {
//	@XStreamAsAttribute
//	@XStreamAlias("user_id")
	private int userId;
//	@XStreamAlias("user_name")
	private String userName;
//	@XStreamAlias("password")
	private String password;
//	@XStreamImplicit
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

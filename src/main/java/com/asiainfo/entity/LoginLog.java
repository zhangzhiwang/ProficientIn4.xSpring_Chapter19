package com.asiainfo.entity;

import java.sql.Timestamp;

public class LoginLog {
	private int loginId;
	private Timestamp loginTime;

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "LoginLog [loginId=" + loginId + ", loginTime=" + loginTime + "]";
	}

}

package com.asiainfo.entity;

import java.sql.Timestamp;

import com.asiainfo.util.DateConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("loginLog")
public class LoginLog {
	@XStreamAlias("login_id")
	private int loginId;
	@XStreamAlias("login_time")
	@XStreamConverter(DateConverter.class)
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

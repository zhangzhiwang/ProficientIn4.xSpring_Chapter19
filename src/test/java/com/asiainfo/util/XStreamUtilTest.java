package com.asiainfo.util;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asiainfo.entity.LoginLog;
import com.asiainfo.entity.User;

import junit.framework.TestCase;

public class XStreamUtilTest extends TestCase {

	public void testObject2Xml() throws Exception {
		List<LoginLog> loginLogList = new ArrayList<LoginLog>();
		LoginLog loginLog1 = new LoginLog();
		loginLog1.setLoginId(1);
		loginLog1.setLoginTime(new Timestamp(new Date().getTime()));
		loginLogList.add(loginLog1);
		
		LoginLog loginLog2 = new LoginLog();
		loginLog2.setLoginId(2);
		loginLog2.setLoginTime(new Timestamp(new Date().getTime()));
		loginLogList.add(loginLog2);
		
		User user = new User();
		user.setUserId(123);
		user.setUserName("aaa");
		user.setPassword("1234");
		user.setLoginLogList(loginLogList);
		
//		XStreamUtil.object2Xml(user, "/Users/zhangzhiwang/Desktop/xStreamSample.xml");
//		XStreamUtil.user2Xml(user, "/Users/zhangzhiwang/Desktop/xStreamSample_user.xml");
		User result = (User) XStreamUtil.xml2Object("/Users/zhangzhiwang/Desktop/xStreamSample.xml");
		System.out.println(result);
	}

}

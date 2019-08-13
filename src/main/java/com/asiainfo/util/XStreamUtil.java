package com.asiainfo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import com.asiainfo.entity.LoginLog;
import com.asiainfo.entity.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

/**
 * XStream工具类
 *
 * @author zhangzhiwang
 * @date Aug 12, 2019 2:27:47 PM
 */
public class XStreamUtil {
	private static XStream xStream;

	static {
		xStream = new XStream();
	}

	/**
	 * Java类转成xml文件
	 * 
	 * @param object
	 * @param fileName
	 * @throws FileNotFoundException
	 * @author zhangzhiwang
	 * @date Aug 13, 2019 11:36:00 AM
	 */
	public static void object2Xml(Object object, String fileName) throws Exception {
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
//		xStream.registerConverter(new DateConverter());
		xStream.toXML(object, fileOutputStream);
	}

	/**
	 * Java类转成xml文件（使用注解）
	 * 
	 * @param object
	 * @param fileName
	 * @throws Exception
	 * @author zhangzhiwang
	 * @date Aug 13, 2019 4:02:09 PM
	 */
	public static void object2XmlAnnotation(Object object, String fileName) throws Exception {
		// 手动注册标注了XStream注解的Java类
//		xStream.processAnnotations(User.class);
//		xStream.processAnnotations(LoginLog.class);
		// 自动注册注了XStream注解的Java类
		xStream.autodetectAnnotations(true);

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		xStream.toXML(object, fileOutputStream);
	}

	/**
	 * Java类转成xml文件（使用流）
	 * 
	 * @param object
	 * @param fileName
	 * @throws Exception
	 * @author zhangzhiwang
	 * @date Aug 13, 2019 4:02:09 PM
	 */
	public static void object2XmlStream(Object object, String fileName) throws Exception {
		// 手动注册标注了XStream注解的Java类
//		xStream.processAnnotations(User.class);
//		xStream.processAnnotations(LoginLog.class);
		// 自动注册注了XStream注解的Java类
		xStream.autodetectAnnotations(true);

		PrintWriter printWriter = new PrintWriter(fileName);
		PrettyPrintWriter prettyPrintWriter = new PrettyPrintWriter(printWriter);
		ObjectOutputStream objectOutputStream = xStream.createObjectOutputStream(prettyPrintWriter);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
	}

	/**
	 * User实体类转换成xml
	 * 
	 * @throws Exception
	 * @author zhangzhiwang
	 * @date Aug 13, 2019 1:26:10 PM
	 */
	public static void user2Xml(User user, String fileName) throws Exception {
		// 设置类别名，默认为类的全限定名
		xStream.alias("user", User.class);
		xStream.alias("loginLog", LoginLog.class);
		// 设置类属性别名
		xStream.aliasField("USER_ID", User.class, "userId");
		xStream.aliasField("USER_NAME", User.class, "userName");
		xStream.aliasField("PASSWORD", User.class, "password");
		xStream.aliasField("LOGIN_TIME", LoginLog.class, "loginTime");
		// 将类属性作为xml标签的属性，默认类属性为类标签的子标签
		xStream.useAttributeFor(LoginLog.class, "loginId");
		// 给xml属性设置别名
		xStream.aliasAttribute(LoginLog.class, "loginId", "L_ID");
		// 去掉集合的父节点
		xStream.addImplicitCollection(User.class, "loginLogList");
		// 注册自定义转换器
		xStream.registerConverter(new DateConverter());

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		xStream.toXML(user, fileOutputStream);
	}

	/**
	 * 将xml转换为实体类
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @author zhangzhiwang
	 * @date Aug 13, 2019 1:44:28 PM
	 */
	public static Object xml2Object(String fileName) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(fileName);
		xStream.registerConverter(new DateConverter());
		return xStream.fromXML(fileInputStream);
	}

	/**
	 * 将xml转换为实体类（使用流）
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @author zhangzhiwang
	 * @date Aug 13, 2019 1:44:28 PM
	 */
	public static Object xml2ObjectStream(String fileName) throws Exception {
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ObjectInputStream objectInputStream = xStream.createObjectInputStream(bufferedReader);
		return objectInputStream.readObject();
	}
	
	/**
	 * 持久化
	 * 
	 * @param fileName
	 * @author zhangzhiwang
	 * @date Aug 13, 2019 4:53:12 PM
	 */
	public static void persist(List objectList, String filePath) {
		File file = new File(filePath);
		PersistenceStrategy persistenceStrategy = new FilePersistenceStrategy(file);
		List list = new XmlArrayList(persistenceStrategy);
		list.addAll(objectList);
	}
	
	/**
	 * Java对象转JSON
	 * 
	 * @param object
	 * @param fileName
	 * @author zhangzhiwang
	 * @throws Exception 
	 * @date Aug 13, 2019 5:07:21 PM
	 */
	public static void object2Json(Object object, String fileName) throws Exception {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8"));
		xStream = new XStream(new JettisonMappedXmlDriver());// 生成无格式的json
		xStream = new XStream(new JsonHierarchicalStreamDriver());// 生成格式化后的json
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.alias("user", User.class);
		xStream.toXML(object, outputStreamWriter);
	}
}

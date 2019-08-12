package com.asiainfo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;

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
	
	public static void object2Xml(Object object) throws FileNotFoundException {
		FileOutputStream fileOutputStream = new FileOutputStream("out/xStreamSample.xml");
		xStream.toXML(object, fileOutputStream);
	}
}

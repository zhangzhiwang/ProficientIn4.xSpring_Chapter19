package com.asiainfo.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 日期转换类
 *
 * @author zhangzhiwang
 * @date Aug 13, 2019 1:50:51 PM
 */
public class DateConverter implements Converter {

	/**
	 *	判断是否能够进行转换
	 */
	public boolean canConvert(Class type) {
		return Timestamp.class.isAssignableFrom(type);// 是Timestamp类型的才能转
	}

	/**
	 *	Java对象转xml的逻辑
	 */
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		writer.setValue(simpleDateFormat.format(source));
	}

	/**
	 *	xml转Java对象的逻辑
	 */
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy%MM%dd hh:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(reader.getValue());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}

}

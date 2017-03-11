package com.llhao.gobang.converter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

/**
 * @author ÂÞºÆ
 **/
public class DateFormatConverter implements Converter<String, Date> {

	public Date convert(String source) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date data = format.parse(source);
			Date value = new Date(data.getTime());
			return value;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}

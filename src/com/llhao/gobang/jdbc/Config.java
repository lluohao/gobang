package com.llhao.gobang.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ÂÞºÆ
 * 
 **/
public class Config {
	private InputStream data;
	private Properties properties;

	public Config(InputStream data) throws IOException {
		this.data = data;
		properties = new Properties();
		properties.load(this.data);
	}

	public String get(String key) {
		return properties.getProperty(key);
	}

}

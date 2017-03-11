package com.llhao.gobang.factory;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

import redis.clients.jedis.Jedis;

/**
 * @author ÂÞºÆ
 *
 **/
public class JedisFactory implements FactoryBean<Jedis> {
	private static Jedis jedis;
	private String password;
	private String host = "localhost";
	private int port = 6379;

	public Jedis getObject() throws Exception {
		if (jedis == null) {
			synchronized (this) {
				if (jedis == null) {
					jedis = new Jedis(host);
					if (password != null) {
						jedis.auth(password);
					}
				}
			}
		}
		return jedis;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Class<?> getObjectType() {
		return Jedis.class;
	}

	public boolean isSingleton() {
		return true;
	}

}

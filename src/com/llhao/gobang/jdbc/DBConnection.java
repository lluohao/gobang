package com.llhao.gobang.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 罗浩
 * 
 **/
public class DBConnection {
	/**
	 * 使用懒加载的单例模式
	 */
	private static DBConnection instance;
	/**
	 * 连接集合
	 */
	private static List<Connection> conns;
	/**
	 * 相关配置信息
	 */
	private static Config config;
	/**
	 * 静态代码块，读取配置数据
	 */
	static {
		try {
			config = new Config(
					DBConnection.class
							.getResourceAsStream("/conf/db.properties"));
			conns = new ArrayList<Connection>();
			Class.forName(config.get("driverName"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private DBConnection() {

	}

	/**
	 * 返回一个DBConnection的实例
	 * 
	 * @return
	 */
	public static DBConnection getInstance() {
		if (instance == null) {
			synchronized (config) {
				if (instance == null) {
					instance = new DBConnection();
				}
			}
		}
		return instance;
	}

	/**
	 * 获取一个连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		synchronized (conns) {
			if (conns.size() > 0) {
				return conns.remove(0);
			}
		}
		try {
			return getConnection0();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Connection getConnection0() throws SQLException {
		String name = config.get("username");
		String password = config.get("password");
		String url = config.get("url");
		return DriverManager.getConnection(url, name, password);
	}

	/**
	 * 关闭资源，Statement,ResultSet会直接关闭，而Connection会被回收保存到集合中
	 * 
	 * @param sttm
	 * @param conn
	 * @param rs
	 */
	public void close(Statement sttm, Connection conn, ResultSet rs) {

		try {
			if (sttm != null) {
				sttm.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conns.add(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

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
 * @author �޺�
 * 
 **/
public class DBConnection {
	/**
	 * ʹ�������صĵ���ģʽ
	 */
	private static DBConnection instance;
	/**
	 * ���Ӽ���
	 */
	private static List<Connection> conns;
	/**
	 * ���������Ϣ
	 */
	private static Config config;
	/**
	 * ��̬����飬��ȡ��������
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
	 * ����һ��DBConnection��ʵ��
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
	 * ��ȡһ������
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
	 * �ر���Դ��Statement,ResultSet��ֱ�ӹرգ���Connection�ᱻ���ձ��浽������
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

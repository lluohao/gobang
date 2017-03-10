package com.llhao.gobang.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.llhao.gobang.jdbc.DBConnection;
import com.llhao.gobang.jdbc.Entity;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author 罗浩
 * 
 **/
public class EntityDao {
	/**
	 * 根据entity中的条件查询，这个方法无法处理SQL注入攻击
	 * 
	 * @param entity
	 *            条件限制，如果entity中的字段不为null，则视为查询条件
	 * @return 结果集
	 */
	public List<Entity> find(Entity entity) {
		/**
		 * 获取连接
		 */
		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = dbConn.getConnection();
		PreparedStatement psttm = null;
		ResultSet rs = null;
		/**
		 * 使用反射处理
		 */
		Class<? extends Entity> cls = entity.getClass();
		Field[] fields = cls.getDeclaredFields();
		List<Entity> results = new ArrayList<Entity>();
		/**
		 * 拼接sql语句，如果属性的值不为null则认为其为查询条件之一
		 */
		StringBuilder sql = new StringBuilder("SELECT * FROM "
				+ entity.tableName() + " WHERE 1=1");
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				Object obj = field.get(entity);
				if (obj != null) {
					sql.append(" AND " + entity.fieldName(field) + "=" + obj);
				}
			}
			/**
			 * sql语句拼接完成，开始查询
			 */
			psttm = (PreparedStatement) conn.prepareStatement(sql.toString());
			rs = psttm.executeQuery();
			/**
			 * 解析结果
			 */
			ResultSetMetaData rsm = rs.getMetaData();
			/**
			 * 获取数据库所有字段名
			 */
			String[] titles = new String[rsm.getColumnCount()];
			for (int i = 0; i < titles.length; i++) {
				titles[i] = rsm.getColumnName(i + 1);
			}
			/**
			 * 循环遍历每一行
			 */
			while (rs.next()) {
				Entity en = cls.newInstance();
				for (Field field : fields) {
					field.setAccessible(true);
					for (String fieldName : titles) {
						if (entity.fieldName(field).equals(fieldName)) {
							field.set(en, rs.getObject(fieldName));
						}
					}
				}
				results.add(en);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public void save(Entity entity) {
		Class<? extends Entity> cls = entity.getClass();
		Field[] fields = cls.getDeclaredFields();
		List<String> column = new ArrayList<String>();
		List<Object> value = new ArrayList<Object>();
		for (Field field : fields) {
			field.setAccessible(true);
			try {

				column.add(entity.fieldName(field));
				value.add(field.get(entity));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		StringBuilder builder = new StringBuilder("INSERT INTO "
				+ entity.tableName() + "(");
		for (int i = 0; i < column.size(); i++) {
			String str = column.get(i);
			if (i != column.size() - 1) {
				builder.append(str + ",");
			} else {
				builder.append(str);
			}
		}
		builder.append(") VALUES(");
		for (int i = 0; i < value.size(); i++) {
			Object obj = value.get(i);
			if (i != column.size() - 1) {
				builder.append(obj + ",");
			} else {
				builder.append(obj);
			}
		}
		builder.append(")");
		System.out.println(builder.toString());
	}
}

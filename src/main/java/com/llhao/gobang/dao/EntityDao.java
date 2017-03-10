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
 * @author �޺�
 * 
 **/
public class EntityDao {
	/**
	 * ����entity�е�������ѯ����������޷�����SQLע�빥��
	 * 
	 * @param entity
	 *            �������ƣ����entity�е��ֶβ�Ϊnull������Ϊ��ѯ����
	 * @return �����
	 */
	public List<Entity> find(Entity entity) {
		/**
		 * ��ȡ����
		 */
		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = dbConn.getConnection();
		PreparedStatement psttm = null;
		ResultSet rs = null;
		/**
		 * ʹ�÷��䴦��
		 */
		Class<? extends Entity> cls = entity.getClass();
		Field[] fields = cls.getDeclaredFields();
		List<Entity> results = new ArrayList<Entity>();
		/**
		 * ƴ��sql��䣬������Ե�ֵ��Ϊnull����Ϊ��Ϊ��ѯ����֮һ
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
			 * sql���ƴ����ɣ���ʼ��ѯ
			 */
			psttm = (PreparedStatement) conn.prepareStatement(sql.toString());
			rs = psttm.executeQuery();
			/**
			 * �������
			 */
			ResultSetMetaData rsm = rs.getMetaData();
			/**
			 * ��ȡ���ݿ������ֶ���
			 */
			String[] titles = new String[rsm.getColumnCount()];
			for (int i = 0; i < titles.length; i++) {
				titles[i] = rsm.getColumnName(i + 1);
			}
			/**
			 * ѭ������ÿһ��
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

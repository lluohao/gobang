package com.llhao.gobang.jdbc;

import java.lang.reflect.Field;

/**
 * @author �޺�
 * 
 **/
public interface Entity {
	String fieldName(Field field);

	String tableName();
}

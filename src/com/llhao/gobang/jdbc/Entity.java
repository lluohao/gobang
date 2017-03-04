package com.llhao.gobang.jdbc;

import java.lang.reflect.Field;

/**
 * @author ÂÞºÆ
 * 
 **/
public interface Entity {
	String fieldName(Field field);

	String tableName();
}

package com.llhao.gobang.dao;

import java.util.List;

import com.llhao.gobang.entity.Record;

/**
 *@author ÂÞºÆ
 *
 **/
public interface IRecordDAO {

	public abstract void save(Record transientInstance);

	public abstract void delete(Record persistentInstance);

	public abstract Record findById(java.lang.Integer id);

	public abstract List findByExample(Record instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByFile(Object file);

	public abstract List findByWin(Object win);

	public abstract List findAll();

	public abstract Record merge(Record detachedInstance);

	public abstract void attachDirty(Record instance);

	public abstract void attachClean(Record instance);

}
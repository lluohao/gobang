package com.llhao.gobang.dao;

import java.util.List;

import com.llhao.gobang.entity.User;

/**
 *@author ÂÞºÆ
 *
 **/
public interface IUserDAO {

	public abstract int save(User transientInstance);

	public abstract void delete(User persistentInstance);

	public abstract User findById(java.lang.Integer id);

	public abstract List<User> findByExample(User instance);

	public abstract List<User> findByProperty(String propertyName, Object value);

	public abstract List<User> findByName(Object name);

	public abstract List<User> findByPassword(Object password);

	public abstract List<User> findByEmail(Object email);

	public abstract List<User> findByImage(Object image);

	public abstract List<User> findByWin(Object win);

	public abstract List<User> findByTie(Object tie);

	public abstract List<User> findByLose(Object lose);

	public abstract List<User> findAll();

	public abstract User merge(User detachedInstance);

	public abstract void attachDirty(User instance);

	public abstract void attachClean(User instance);

}
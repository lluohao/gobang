package com.llhao.gobang.service;

import com.llhao.gobang.entity.User;

/**
 *@author ÂÞºÆ
 *
 **/
public interface IUserService {

	User register(String name, String password, String email);

	User login(String name, String password);
	
	void findPassword(String name);

}

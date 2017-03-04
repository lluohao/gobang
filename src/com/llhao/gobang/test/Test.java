package com.llhao.gobang.test;

import com.llhao.gobang.dao.impl.UserDAO;


/**
 * @author ÂÞºÆ
 * 
 **/
public class Test {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		System.out.println(dao.findAll());
	}

}

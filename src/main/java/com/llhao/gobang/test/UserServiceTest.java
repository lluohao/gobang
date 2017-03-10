package com.llhao.gobang.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.impl.UserServiceImpl;

/**
 *@author ÂÞºÆ
 *
 **/
public class UserServiceTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
		UserServiceImpl service = (UserServiceImpl) ctx.getBean("userService");
		User user = service.register("324", "w43542345", "d");
		System.out.println(user.getId());
	}
}

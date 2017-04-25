package com.llhao.gobang.dao;

import com.llhao.gobang.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

/**
 * Created by llhao on 2017/4/25.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IUserDao dao = (IUserDao) context.getBean("userDao");
        User user = new User();
        user.setName("金三胖");
        user.setPassword("123457");
        user.setEmail(user.getName().hashCode()+"@qq.com");
        user.setTime(new Date(System.currentTimeMillis()));
        int id = dao.addUser(user);
        System.out.println(id);
    }
}

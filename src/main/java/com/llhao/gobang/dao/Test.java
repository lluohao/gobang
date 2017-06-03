package com.llhao.gobang.dao;

import com.llhao.gobang.dao.jo.UpdateUserJO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by llhao on 2017/4/25.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IUserDao dao = context.getBean(IUserDao.class);
        UpdateUserJO jo = new UpdateUserJO();
        jo.setType(3);
        jo.setUserId(4);
        int result = dao.updateUser(jo);
        System.out.println(result);
        System.exit(-1);
    }
}

package com.llhao.gobang.service.impl;

import com.llhao.gobang.dao.IUserDao;
import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.IUserService;
import com.llhao.gobang.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by llhao on 2017/4/25.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    public User addUser(String name, String password, String email) {
        if (name == null || name.length() < 2) {
            throw new RuntimeException("用户用太短");
        }
        if (name.length() > 20) {
            throw new RuntimeException("用户名太长");
        }
        if (password == null || password.length() < 6) {
            throw new RuntimeException("密码长度不够");
        }
        if (password.length() > 16) {
            throw new RuntimeException("密码过长");
        }
        if (!StringUtils.isEmail(email)) {
            throw new RuntimeException("请输入正确的邮箱地址");
        }
        if (userDao.findUserByEmail(email) != null) {
            throw new RuntimeException("此邮箱已经被注册");
        }
        if (userDao.findUserByName(name) != null) {
            throw new RuntimeException("用户名已经被注册");
        }
        User user = new User();
        user.setTime(new Date(System.currentTimeMillis()));
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        userDao.addUser(user);
        user = userDao.findUserByName(name);
        return user;
    }

    public boolean checkName(String name) {
        if (name == null) {
            return false;
        }
        return userDao.findUserByName(name) == null;
    }

    public User login(String name, String password) {
        User user = userDao.findUserByName(name);
        if (user == null) {
            throw new RuntimeException("用户名不存在");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findById(int id){
        return userDao.findUserById(id);
    }
}

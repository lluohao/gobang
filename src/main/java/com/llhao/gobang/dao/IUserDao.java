package com.llhao.gobang.dao;

import com.llhao.gobang.entity.User;

/**
 * Created by llhao on 2017/4/24.
 */
public interface IUserDao {
    User findUserById(int id);

    User findUserByName(String name);

    User findUserByEmail(String email);

    int addUser(User user);
}

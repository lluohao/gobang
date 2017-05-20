package com.llhao.gobang.service;

import com.llhao.gobang.entity.User;

/**
 * Created by llhao on 2017/4/25.
 */
public interface IUserService {
    User addUser(String name, String password, String email);

    boolean checkName(String name);

    User login(String name, String password);

    User findById(int id);
}

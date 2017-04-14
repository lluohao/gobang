package com.llhao.gobang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HAO zong on 2017-4-13.
 */
@Controller
public class  UserController{
    public UserController() {
        System.out.println("Init.....");
    }

    public @ResponseBody String login(String name, String password){
        return "Hello! "+name;
    }
}

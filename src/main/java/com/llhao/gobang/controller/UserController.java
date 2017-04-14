package com.llhao.gobang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by HAO zong on 2017-4-13.
 */
@Controller
public class  UserController{
    @RequestMapping("/login")
    public  String login(@RequestParam String name, @RequestParam String password, Model model){
        model.addAttribute("message","登录失败！");
        return "login";
    }
}
